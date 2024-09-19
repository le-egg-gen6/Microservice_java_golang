package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 1:30 AM Fri 9/20/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSamePromotionProductRequest {

    @JsonProperty("promotionId")
    private Long promotionId;

}
