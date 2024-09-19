package com.myproject.product_service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.dto.ProductDTO;
import com.myproject.product_service.dto.PromotionDTO;
import com.myproject.product_service.payload.shared.PagingAndSortingResponse;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 1:32 AM Fri 9/20/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionWithProductResponse extends PagingAndSortingResponse {

    @JsonProperty("promotion")
    private PromotionDTO promotion;

    @JsonProperty("products")
    private List<ProductDTO> products;
}
