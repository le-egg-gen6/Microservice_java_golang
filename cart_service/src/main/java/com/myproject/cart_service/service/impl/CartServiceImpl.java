package com.myproject.cart_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.cart_service.entity.Cart;
import com.myproject.cart_service.entity.CartState;
import com.myproject.cart_service.exception.ItemNotFoundException;
import com.myproject.cart_service.kafka.KafkaProducerService;
import com.myproject.cart_service.repository.CartRepository;
import com.myproject.cart_service.service.CartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final int CORE_POOL_SIZE = 1;

    private final int MAX_POOL_SIZE = 5;

    private final int THREAD_LIVE_TIME_IN_MIN = 1;

    private final CartRepository cartRepository;

    private final KafkaProducerService kafkaProducerService;

    private Cache<String, String> userId2CartId;

    private Cache<String, Cart> cartId2Cart;

    private ThreadPoolExecutor executors;

    @PostConstruct
    private void initialize() {
        userId2CartId = Caffeine.newBuilder()
                .maximumSize(1_000)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build();

        cartId2Cart = Caffeine.newBuilder()
                .maximumSize(1_000)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build();

        executors = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                THREAD_LIVE_TIME_IN_MIN,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()
        );
    }

    private void addAsynchronousTask(Runnable task) {
        executors.submit(task);
    }


    @Override
    public void saveAsync(Cart cart) {
        addAsynchronousTask(() -> cartRepository.save(cart));
    }

    @Override
    public Cart getCartByUserId(String userId) {
        String cartId = userId2CartId.getIfPresent(userId);
        Cart cart = null;
        if (cartId == null) {
            cart = cartRepository.findByUserIdAndState(userId, CartState.UNCONFIRMED.getValue()).orElse(null);
            if (cart == null) {
                throw new ItemNotFoundException();
            }
            userId2CartId.put(userId, cart.getId());
            cartId2Cart.put(cart.getId(), cart);
        } else {
            cart = cartId2Cart.getIfPresent(cartId);
            if (cart == null) {
                cart = cartRepository.findById(cartId).orElse(null);
                if (cart == null) {
                    userId2CartId.invalidate(userId);
                    throw new ItemNotFoundException();
                }
                cartId2Cart.put(cartId, cart);
            }
        }
        return cart;
    }

    @Override
    public Cart getCartByCartId(String cartId) {
        Cart cart = cartId2Cart.getIfPresent(cartId);
        if (cart == null) {
            cart = cartRepository.findById(cartId).orElse(null);
            if (cart == null) {
                throw new ItemNotFoundException();
            }
            cartId2Cart.put(cartId, cart);
        }
        return cart;
    }


    @Override
    public void increaseItemQuantity(String cartId, Long itemId) {
        Cart cart = getCartByCartId(cartId);
        if (cart == null || !cart.isCartModifiable()) {
            return;
        }
        Map<Long, Integer> products = cart.getProducts();
        if (!products.containsKey(itemId)) {
            return;
        }
        products.put(itemId, products.get(itemId) + 1);
        saveAsync(cart);
    }

    @Override
    public void decreaseItemQuantity(String cartId, Long itemId) {
        Cart cart = getCartByCartId(cartId);
        if (cart == null || !cart.isCartModifiable()) {
            return;
        }
        Map<Long, Integer> products = cart.getProducts();
        if (!products.containsKey(itemId)) {
            return;
        }
        products.put(itemId, products.get(itemId) - 1);
        if (products.get(itemId) == 0) {
            products.remove(itemId);
        }
        saveAsync(cart);
    }

    @Override
    public void setItemQuantity(String cartId, Long itemId, Integer quantity) {
        Cart cart = getCartByCartId(cartId);
        if (cart == null || !cart.isCartModifiable()) {
            return;
        }
        Map<Long, Integer> products = cart.getProducts();
        if (!products.containsKey(itemId)) {
            return;
        }
        products.put(itemId, quantity);
        saveAsync(cart);
    }

    @Override
    public void confirmPurchase(String cartId) {

    }
}
