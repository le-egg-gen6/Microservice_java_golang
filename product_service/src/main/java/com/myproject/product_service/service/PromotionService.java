package com.myproject.product_service.service;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.payload.request.CreatePromotionRequest;
import com.myproject.product_service.payload.request.UpdatePromotionRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author nguyenle
 */
public interface PromotionService {

    PromotionDTO createPromotion(CreatePromotionRequest request);

    List<PromotionDTO> getAllPromotion(Pageable pageable);

    PromotionDTO getPromotionById(Long id);

    PromotionDTO updatePromotionInfo(Long id, UpdatePromotionRequest request);

}
