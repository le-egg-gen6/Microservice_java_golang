package com.myproject.cart_service.service;

import com.myproject.cart_service.entity.Cart;

/**
 * @author nguyenle
 */
public interface CartService {

    void saveAsync(Cart cart);

    Cart getCartByUserId(String userId);

    Cart getCartByCartId(String cartId);

    void increaseItemQuantity(String cartId, Long itemId);

    void decreaseItemQuantity(String cartId, Long itemId);

    void setItemQuantity(String cartId, Long itemId, Integer quantity);

    int confirmPurchase(String cartId);
}
