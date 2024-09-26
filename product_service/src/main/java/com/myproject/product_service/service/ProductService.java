package com.myproject.product_service.service;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.request.CreateProductRequest;
import com.myproject.product_service.payload.request.UpdateProductRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * @author nguyenle
 */
public interface ProductService {

    ProductDTO createProduct(CreateProductRequest request);

    ProductDTO getProductById(Long id);

    ProductDTO updateProductInfo(Long id, UpdateProductRequest request);

    List<ProductDTO> getAllProduct(Pageable pageable);

    List<ProductDTO> getSameCategoryProducts(Long categoryId, Pageable pageable);

    List<ProductDTO> getSamePromotionProducts(Long promotionId, Pageable pageable);

}
