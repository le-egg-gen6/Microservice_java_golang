package com.myproject.product_service.controller;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.request.GetSameCategoryProductRequest;
import com.myproject.product_service.payload.response.ProductResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author nguyenle
 */
@RestController
@RequestMapping("/public/product")
@RequiredArgsConstructor
public class PublicProductController {

    private final ProductService productService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<?>> getProductInfo(
            @RequestParam("id") Long id
    ) {
        ProductDTO productDTO = productService.getProductById(id);
        ProductResponse response = ProductResponse.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .imageUrl(productDTO.getImageUrl())
                .likesCount(productDTO.getLikesCount())
                .categories(productDTO.getCategories())
                .promotions(productDTO.getPromotions())
                .build();
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }

    @PostMapping("/find-by-category")
    public ResponseEntity<ApiResponse<?>> findProductsByCategory(
            @RequestBody GetSameCategoryProductRequest request
    ) {

    }
}
