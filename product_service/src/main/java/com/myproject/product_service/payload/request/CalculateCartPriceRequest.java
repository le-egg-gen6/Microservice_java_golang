package com.myproject.product_service.payload.request;

import lombok.*;

import java.util.Map;

/**
 * @author nguyenle
 * @since 11:38 AM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculateCartPriceRequest {

    private Map<Long, Integer> products;

}
