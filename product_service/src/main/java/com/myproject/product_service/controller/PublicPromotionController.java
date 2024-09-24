package com.myproject.product_service.controller;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.payload.response.PromotionResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
