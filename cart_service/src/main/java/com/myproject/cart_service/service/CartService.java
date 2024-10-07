package com.myproject.cart_service.service;

import com.myproject.cart_service.entity.Cart;

/**
 * @author nguyenle
 */
public interface CartService {

    void saveAsync(Cart cart);

    Cart getCart(String userId);

}
