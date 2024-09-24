package com.myproject.product_service.service;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.request.CreateProductRequest;
import com.myproject.product_service.payload.request.UpdateProductRequest;

/**
 * @author nguyenle
 */
public interface ProductService {

    ProductDTO createProduct(CreateProductRequest request);

    ProductDTO getProductById(Long id);

    ProductDTO updateProductInfo(Long id, UpdateProductRequest request);

}
