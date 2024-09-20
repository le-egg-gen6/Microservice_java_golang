package com.myproject.product_service.mapper;

import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author nguyenle
 * @since 1:05 AM Sat 9/21/2024
 */
@Mapper(
        componentModel = "spring",
        uses = {
                CategoryMapper.class,
                PromotionMapper.class
        }
)
public interface ProductMapper {

    @Mapping(target = "categories", qualifiedByName = "categoryToDTO")
    @Mapping(target = "promotions", qualifiedByName = "promotionToDTO")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    ProductDTO productToProductDTO(Product product);

    @Mapping(target = "categories", qualifiedByName = "categoryToEntity")
    @Mapping(target = "promotions", qualifiedByName = "promotionToEntity")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Product productDTOToProduct(ProductDTO productDTO);

    List<ProductDTO> productsToProductDTOs(List<Product> products);
    List<Product> productDTOsToProducts(List<ProductDTO> productDTOs);

}
