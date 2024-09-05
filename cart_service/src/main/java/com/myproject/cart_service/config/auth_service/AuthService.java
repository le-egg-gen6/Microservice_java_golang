package com.myproject.cart_service.config.auth_service;

/**
 * @author nguyenle
 */
public interface AuthService {

    AuthResponse validate(String token);

}
