package com.myproject.product_service.mapper;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.entity.Product;
import com.myproject.product_service.entity.Promotion;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Set;

/**
 * @author nguyenle
 * @since 6:31 AM Tue 9/17/2024
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product, Set<Promotion> promotions);

    @AfterMapping
    default void calculateProductPrice(@MappingTarget ProductDTO productDTO) {

    }

}
