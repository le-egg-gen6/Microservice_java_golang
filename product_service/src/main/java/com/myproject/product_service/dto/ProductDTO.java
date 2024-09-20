package com.myproject.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 6:26 AM Tue 9/17/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("stockQuantity")
    private Integer stockQuantity;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("likesCount")
    private Integer likesCount;

    @JsonProperty("categories")
    private List<CategoryDTO> categories;

    @JsonProperty("promotions")
    private List<PromotionDTO> promotions;

}
