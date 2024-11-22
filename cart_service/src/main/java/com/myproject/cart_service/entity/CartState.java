package com.myproject.cart_service.entity;

import lombok.Getter;

/**
 * @author nguyenle
 * @since 8:51 PM Mon 10/7/2024
 */
@Getter
public enum CartState {

    UNCONFIRMED(0),

    PROCESSING(1),

    BUY_SUCCESS(2),

    BUY_FAIL(3)
    ;

    private int value;

    CartState(int val) {
        this.value = val;
    }

}
