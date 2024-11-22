package com.myproject.cart_service.config.product_service;

import lombok.*;

/**
 * @author nguyenle
 * @since 3:40 AM Sun 10/13/2024
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
