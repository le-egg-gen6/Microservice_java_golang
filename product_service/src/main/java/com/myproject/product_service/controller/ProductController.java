package com.myproject.product_service.controller;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.request.CreateProductRequest;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> createNewProduct(
            @RequestBody CreateProductRequest createProductRequest
    ) {
        ProductDTO productDTO = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(ApiResponse.successResponse(productDTO));
    }
}
