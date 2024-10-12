package com.myproject.cart_service.exception;

/**
 * @author nguyenle
 * @since 1:35 PM Sat 10/12/2024
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
