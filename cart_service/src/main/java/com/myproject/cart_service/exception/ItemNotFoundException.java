package com.myproject.cart_service.exception;

/**
 * @author nguyenle
 * @since 1:36 PM Sat 10/12/2024
 */
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
