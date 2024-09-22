package com.myproject.product_service.controller.secured;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.payload.request.CreateProductRequest;
import com.myproject.product_service.payload.response.ProductResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 1:45 AM Mon 9/23/2024
 */
@RestController
@RequestMapping("/secured/product")
@RequiredArgsConstructor
public class SecuredProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<?>> createNewProduct(
            @RequestBody CreateProductRequest createProductRequest
    ) {
        ProductDTO productDTO = productService.createProduct(createProductRequest);
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

}
