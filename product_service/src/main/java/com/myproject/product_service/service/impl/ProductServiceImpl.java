package com.myproject.product_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.product_service.config.media_service.MediaResponse;
import com.myproject.product_service.config.media_service.MediaService;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.entity.Category;
import com.myproject.product_service.entity.Product;
import com.myproject.product_service.entity.Promotion;
import com.myproject.product_service.exception.ItemNotFoundException;
import com.myproject.product_service.mapper.ProductMapper;
import com.myproject.product_service.payload.request.CreateProductRequest;
import com.myproject.product_service.payload.request.UpdateProductRequest;
import com.myproject.product_service.repository.ProductRepository;
import com.myproject.product_service.service.CategoryService;
import com.myproject.product_service.service.ProductService;

import java.util.Comparator;
import java.util.List;

import com.myproject.product_service.service.PromotionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final int CORE_POOL_SIZE = 1;

    private final int MAX_POOL_SIZE = 5;

    private final int THREAD_LIFE_TIME_IN_MINUTE = 1;


    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final PromotionService promotionService;

    private final MediaService mediaService;

    private final ProductMapper productMapper;

    private Cache<Long, Product> code2Product;

    private ThreadPoolExecutor executors;

    @PostConstruct
    private void initialize() {
        code2Product = Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .maximumSize(1_000)
                .build();

        executors = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                THREAD_LIFE_TIME_IN_MINUTE,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()
        );
    }

    private void addAsynchronousTask(Runnable task) {
        executors.submit(task);
    }

    @Override
    public void saveAsync(Product product) {
        addAsynchronousTask(() -> productRepository.save(product));
    }

    @Override
    public Product getProduct(Long id) {
        Product product = code2Product.getIfPresent(id);
        if (product == null) {
            product = productRepository.findById(id).orElse(null);
            if (product == null) {
                throw new ItemNotFoundException();
            }
        }
        return product;
    }

    @Override
    public ProductDTO createProduct(CreateProductRequest request) {
        String url = "";
        if (request.getImage() != null) {
            MediaResponse savedImage = mediaService.saveMedia(request.getImage());
            url = savedImage.getUrl();
        }
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription() != null ? request.getDescription() : "")
                .price(request.getPrice())
                .stockQuantity(0)
                .likesCount(0)
                .imageUrl(url)
                .build();
        List<Category> categories = categoryService.getCategoryByIdIn(request.getCategoryIds()).stream()
                .filter(Objects::nonNull)
                .toList();
        List<Promotion> promotions = promotionService.getPromotionByIdIn(request.getPromotionIds()).stream()
                .filter(Objects::nonNull)
                .toList();
        for (Category category : categories) {
            product.addCategory(category);
        }
        for (Promotion promotion : promotions) {
            product.addPromotion(promotion);
        }
        product = productRepository.save(product);
        code2Product.put(product.getId(), product);
        return productMapper.productToProductDTO(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = getProduct(id);
        return productMapper.productToProductDTO(product);
    }

    @Override
    public void likeProduct(Long id) {
        Product product = getProduct(id);
        product.setLikesCount(product.getLikesCount() + 1);
        saveAsync(product);
    }

    @Override
    public ProductDTO updateProductInfo(Long id, UpdateProductRequest request) {
        String url = "";
        if (request.getImage() != null) {
            MediaResponse savedImage = mediaService.saveMedia(request.getImage());
            url = savedImage.getUrl();
        }
        Product product = getProduct(id);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setLikesCount(request.getLikesCount());
        if (!url.isEmpty()) {
            product.setImageUrl(url);
        }
        List<Category> oldCategories = product.getCategories();
        List<Promotion> oldPromotions = product.getPromotions();
        List<Category> newCategories = request.getCategoryIds().stream().map(
                categoryService::getCategory
        ).filter(Objects::nonNull).toList();
        List<Promotion> newPromotions = request.getPromotionIds().stream().map(
                promotionService::getPromotion
        ).filter(Objects::nonNull).toList();
        for (Category category : oldCategories) {
            if (!newCategories.contains(category)) {
                product.removeCategory(category);
            }
        }
        for (Promotion promotion : oldPromotions) {
            if (!newPromotions.contains(promotion)) {
                product.removePromotion(promotion);
            }
        }
        for (Category category : newCategories) {
            if (!oldCategories.contains(category)) {
                product.addCategory(category);
            }
        }
        for (Promotion promotion : newPromotions) {
            if (!oldPromotions.contains(promotion)) {
                product.addPromotion(promotion);
            }
        }
        code2Product.put(id, product);
        product = productRepository.save(product);
        return productMapper.productToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProduct(Pageable pageable) {
        List<Product> products = productRepository.getAllProduct(pageable).stream().toList();
        return products.stream().map(
                product -> {
                    code2Product.put(product.getId(), product);
                    return productMapper.productToProductDTO(product);
                }
        ).toList();
    }

    @Override
    public List<ProductDTO> getSameCategoryProducts(Long categoryId, Pageable pageable) {
        List<Product> products = productRepository.findProductsByCategoryId(categoryId, pageable).stream().toList();
        return products.stream().map(
                product -> {
                    code2Product.put(product.getId(), product);
                    return productMapper.productToProductDTO(product);
                }
        ).toList();
    }

    @Override
    public List<ProductDTO> getSamePromotionProducts(Long promotionId, Pageable pageable) {
        List<Product> products = productRepository.findProductsByPromotionId(promotionId, pageable).stream().toList();
        return products.stream().map(
                product -> {
                    code2Product.put(product.getId(), product);
                    return productMapper.productToProductDTO(product);
                }
        ).toList();
    }

    @Override
    public Double calculateProductPrice(Long productId) {
        Product product = getProduct(productId);
        if (product == null) {
            return 0.0;
        }
        Promotion highestPromotion = product.getPromotions().stream()
                .max(Comparator.comparing(Promotion::getDiscountPercentage))
                .orElse(null);
        if (highestPromotion == null) {
            return Double.valueOf(product.getPrice());
        }
        return product.getPrice() * (100.0d - highestPromotion.getDiscountPercentage()) / 100.0d;
    }
}
