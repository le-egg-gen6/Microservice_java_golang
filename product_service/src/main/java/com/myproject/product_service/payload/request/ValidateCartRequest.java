package com.myproject.product_service.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nguyenle
 * @since 10:02 PM Mon 10/7/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateCartRequest {

    @JsonProperty("products")
    private Map<Long, Integer> products = new HashMap<>();

}
