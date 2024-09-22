package com.myproject.product_service.service;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.request.CreateProductRequest;

/**
 * @author nguyenle
 */
public interface ProductService {

    ProductDTO createProduct(CreateProductRequest request);

    ProductDTO getProductById(Long id);

}
