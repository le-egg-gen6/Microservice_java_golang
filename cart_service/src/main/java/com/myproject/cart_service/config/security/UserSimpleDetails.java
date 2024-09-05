package com.myproject.cart_service.config.security;

import lombok.*;

/**
 * @author nguyenle
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSimpleDetails {

    private String id;

    private String name;

    private String username;

    private String email;

}
