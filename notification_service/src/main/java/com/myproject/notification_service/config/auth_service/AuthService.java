package com.myproject.notification_service.config.auth_service;

/**
 * @author nguyenle
 */
public interface AuthService {

    AuthResponse validate(String token);

}
