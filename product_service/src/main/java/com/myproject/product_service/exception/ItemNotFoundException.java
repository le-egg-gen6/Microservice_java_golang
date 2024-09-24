package com.myproject.product_service.exception;

/**
 * @author nguyenle
 * @since 4:17 PM Tue 9/24/2024
 */
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
