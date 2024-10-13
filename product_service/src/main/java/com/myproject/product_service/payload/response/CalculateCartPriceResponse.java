package com.myproject.product_service.payload.response;

import lombok.*;

/**
 * @author nguyenle
 * @since 11:40 AM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculateCartPriceResponse {

    private Double price;

}
