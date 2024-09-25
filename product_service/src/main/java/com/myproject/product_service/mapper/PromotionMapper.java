package com.myproject.product_service.mapper;

import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author nguyenle
 * @since 1:06 AM Sat 9/21/2024
 */
@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Named("promotionToDTO")
    PromotionDTO promotionToPromotionDTO(Promotion promotion);

    @Named("promotionToEntity")
    Promotion promotionDTOToPromotion(PromotionDTO promotionDTO);

    List<PromotionDTO> promotionsToPromotionDTOs(List<Promotion> promotions);
    List<Promotion> promotionDTOsToPromotions(List<PromotionDTO> promotionDTOs);

}
