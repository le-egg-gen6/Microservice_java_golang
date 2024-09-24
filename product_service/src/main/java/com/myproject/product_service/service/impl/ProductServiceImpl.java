package com.myproject.product_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.product_service.config.media_service.MediaResponse;
import com.myproject.product_service.config.media_service.MediaService;
import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.entity.Category;
import com.myproject.product_service.entity.Product;
import com.myproject.product_service.exception.ItemNotFoundException;
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
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription() != null ? request.getDescription() : "")
                .price(request.getPrice())
                .stockQuantity(0)
                .likesCount(0)
                .imageUrl(savedImage.getUrl())
                .build();
        product = productRepository.save(product);
        code2Product.put(product.getId(), product);
        return productMapper.productToProductDTO(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = code2Product.getIfPresent(id);
        if (product == null) {
            product = productRepository.findById(id).orElse(null);
            if (product != null) {
                code2Product.put(id, product);
            } else {
                throw new ItemNotFoundException();
            }
        }
        return productMapper.productToProductDTO(product);
    }
}
