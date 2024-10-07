package com.myproject.cart_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.cart_service.entity.Cart;
import com.myproject.cart_service.entity.CartState;
import com.myproject.cart_service.repository.CartRepository;
import com.myproject.cart_service.service.CartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private Cache<String, Cart> userId2Cart;

    private ThreadPoolExecutor executors;

    @PostConstruct
    private void initialize() {
        userId2Cart = Caffeine.newBuilder()
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
    public Cart getCart(String userId) {
        Cart cart = userId2Cart.getIfPresent(userId);
        if (cart == null) {
            cart = cartRepository.findByUserIdAndState(userId, CartState.UNCONFIRMED.getValue()).orElse(null);
            if (cart == null) {

            }
            userId2Cart.put(userId, cart);
        }
        return cart;
    }
}
