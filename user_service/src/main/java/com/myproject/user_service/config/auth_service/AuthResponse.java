package com.myproject.user_service.config.auth_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.product_service.config.security.UserSimpleDetails;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    @JsonProperty("authenticated")
    private boolean authenticated;

    @JsonProperty("userSimpleDetails")
    private UserSimpleDetails userSimpleDetails;

    @JsonProperty("roles")
    private List<String> roles;

}
