package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.payload.shared.PagingAndSortingRequest;
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
public class GetSamePromotionProductRequest extends PagingAndSortingRequest {

    @JsonProperty("promotionId")
    private Long promotionId;

}
