package com.myproject.product_service.controller;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.entity.Promotion;
import com.myproject.product_service.payload.response.ListPromotionResponse;
import com.myproject.product_service.payload.response.PromotionResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.payload.shared.PagingAndSortingRequest;
import com.myproject.product_service.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 6:17 AM Tue 9/17/2024
 */
@RestController
@RequestMapping("/public/promotion")
@RequiredArgsConstructor
public class PublicPromotionController {

    private final PromotionService promotionService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<?>> getPromotionInfo(
            @RequestParam("id") Long id
    ) {
        PromotionDTO promotionDTO = promotionService.getPromotionById(id);
        PromotionResponse response = PromotionResponse.builder()
                .id(promotionDTO.getId())
                .name(promotionDTO.getName())
                .description(promotionDTO.getDescription())
                .discountPercentage(promotionDTO.getDiscountPercentage())
                .startDate(promotionDTO.getStartDate())
                .endDate(promotionDTO.getEndDate())
                .build();
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }

    @PostMapping("/find-all")
    public ResponseEntity<ApiResponse<?>> getAllPromotion(
            @RequestBody PagingAndSortingRequest request
    ) {
        Pageable pageable = request.getPageRequest(Promotion.class);
        ListPromotionResponse response = new ListPromotionResponse();
        response.initPageResponse(pageable);
        response.setPromotions(promotionService.getAllPromotion(pageable));
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }

    @PostMapping("/find-by-product")
    public ResponseEntity<ApiResponse<?>> findPromotionsOfExistedProduct(
            @RequestParam("id") Long id,
            @RequestBody PagingAndSortingRequest request
    ) {
        Pageable pageable = request.getPageRequest(Promotion.class);
        ListPromotionResponse response = new ListPromotionResponse();
        response.initPageResponse(pageable);
        response.setPromotions(promotionService.getProductPromotions(id));
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }
}
