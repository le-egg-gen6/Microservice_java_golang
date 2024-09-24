package com.myproject.product_service.service;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.payload.request.CreatePromotionRequest;
import com.myproject.product_service.payload.request.UpdatePromotionRequest;

/**
 * @author nguyenle
 */
public interface PromotionService {

    PromotionDTO createPromotion(CreatePromotionRequest request);

    PromotionDTO getPromotionById(Long id);

    PromotionDTO updatePromotionInfo(Long id, UpdatePromotionRequest request);

}
