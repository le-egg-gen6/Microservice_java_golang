package com.myproject.product_service.controller.secured;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.payload.request.CreatePromotionRequest;
import com.myproject.product_service.payload.response.PromotionResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author nguyenle
 * @since 1:45 AM Mon 9/23/2024
 */
@RestController
@RequestMapping("/secured/promotion")
@RequiredArgsConstructor
public class SecuredPromotionController {

    private final PromotionService promotionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> getPromotionInfo(
            @RequestBody CreatePromotionRequest request
    ) {
        PromotionDTO promotionDTO = promotionService.createPromotion(request);
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
