package com.myproject.product_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.product_service.config.media_service.MediaResponse;
import com.myproject.product_service.config.media_service.MediaService;
import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.entity.Category;
import com.myproject.product_service.entity.Product;
import com.myproject.product_service.mapper.ProductMapper;
import com.myproject.product_service.payload.request.CreateProductRequest;
import com.myproject.product_service.repository.CategoryRepository;
import com.myproject.product_service.repository.ProductRepository;
import com.myproject.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final MediaService mediaService;

    private final ProductMapper productMapper;

    private Cache<Long, Product> code2Product = Caffeine.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();


    @Override
    public ProductDTO createProduct(CreateProductRequest request) {
        MediaResponse savedImage = mediaService.saveMedia(request.getImage());
        List<Category> categories = request.getCategories().stream()
                .map(CategoryDTO::getId)
                .distinct()
                .map(
                        categoryId -> categoryRepository.findById(categoryId)
                                .orElse(null)
                )
                .filter(Objects::nonNull)
                .toList();
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription() != null ? request.getDescription() : "")
                .price(request.getPrice())
                .stockQuantity(0)
                .likesCount(0)
                .categories(categories)
                .build();
        product = productRepository.save(product);
        code2Product.put(product.getId(), product);
        return productMapper.productToProductDTO(product);
    }
}
