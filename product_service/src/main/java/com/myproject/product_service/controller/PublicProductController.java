package com.myproject.product_service.controller;

import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.entity.Product;
import com.myproject.product_service.payload.request.GetSameCategoryProductRequest;
import com.myproject.product_service.payload.request.GetSamePromotionProductRequest;
import com.myproject.product_service.payload.response.CategoryWithProductResponse;
import com.myproject.product_service.payload.response.ProductResponse;
import com.myproject.product_service.payload.response.PromotionWithProductResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.CategoryService;
import com.myproject.product_service.service.ProductService;
import com.myproject.product_service.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    private final CategoryService categoryService;

    private final PromotionService promotionService;

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
        Pageable pageable = request.getPageRequest(Product.class);
        CategoryWithProductResponse response = new CategoryWithProductResponse();
        response.initPageResponse(pageable);
        CategoryDTO categoryDTO = categoryService.getCategoryById(request.getCategoryId());
        response.setCategory(categoryDTO);
        response.setProducts(productService.getSameCategoryProducts(categoryDTO.getId(), pageable));
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }

    @PostMapping("/find-by-promotion")
    public ResponseEntity<ApiResponse<?>> findProductByPromotion(
            @RequestBody GetSamePromotionProductRequest request
    ) {
        Pageable pageable = request.getPageRequest(Product.class);
        PromotionWithProductResponse response = new PromotionWithProductResponse();
        response.initPageResponse(pageable);
        PromotionDTO promotionDTO = promotionService.getPromotionById(request.getPromotionId());
        response.setPromotion(promotionDTO);
        response.setProducts(productService.getSamePromotionProducts(promotionDTO.getId(), pageable));
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }
}
