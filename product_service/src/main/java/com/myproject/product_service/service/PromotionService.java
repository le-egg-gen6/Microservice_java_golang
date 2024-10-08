package com.myproject.product_service.service;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.entity.Promotion;
import com.myproject.product_service.payload.request.CreatePromotionRequest;
import com.myproject.product_service.payload.request.UpdatePromotionRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author nguyenle
 */
public interface PromotionService {

    void saveAsync(Promotion promotion);

    Promotion getPromotion(Long id);

    List<Promotion> getPromotionByIdIn(List<Long> ids);

    PromotionDTO createPromotion(CreatePromotionRequest request);

    List<PromotionDTO> getAllPromotion(Pageable pageable);

    PromotionDTO getPromotionById(Long id);

    PromotionDTO updatePromotionInfo(Long id, UpdatePromotionRequest request);

    List<PromotionDTO> getProductPromotions(Long productId);

}
