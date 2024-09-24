package com.myproject.product_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.entity.Promotion;
import com.myproject.product_service.exception.ItemNotFoundException;
import com.myproject.product_service.mapper.PromotionMapper;
import com.myproject.product_service.payload.request.CreatePromotionRequest;
import com.myproject.product_service.repository.PromotionRepository;
import com.myproject.product_service.service.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    private final PromotionMapper promotionMapper;

    private Cache<Long, Promotion> code2Promotion = Caffeine.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();

    @Override
    public PromotionDTO createPromotion(CreatePromotionRequest request) {
        Promotion promotion = Promotion.builder()
                .name(request.getName())
                .description(request.getDescription())
                .discountPercentage(request.getDiscountPercentage())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
        promotion = promotionRepository.save(promotion);
        code2Promotion.put(promotion.getId(), promotion);
        return promotionMapper.promotionToPromotionDTO(promotion);
    }

    @Override
    public PromotionDTO getPromotionById(Long id) {
        Promotion promotion = code2Promotion.getIfPresent(id);
        if (promotion == null) {
            promotion = promotionRepository.findById(id).orElse(null);
            if (promotion != null) {
                code2Promotion.put(id, promotion);
            } else {
                throw new ItemNotFoundException();
            }
        }
        return promotionMapper.promotionToPromotionDTO(promotion);
    }
}
