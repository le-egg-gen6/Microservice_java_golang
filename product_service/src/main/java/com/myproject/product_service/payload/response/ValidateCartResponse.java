package com.myproject.product_service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nguyenle
 * @since 10:03 PM Mon 10/7/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateCartResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("invalidProducts")
    private Map<Long, Integer> invalidProducts = new HashMap<>();

}
