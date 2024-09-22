package com.myproject.product_service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.dto.PromotionDTO;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 9:58 PM Thu 9/19/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

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
