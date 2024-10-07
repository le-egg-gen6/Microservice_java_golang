package com.myproject.cart_service.config.product_service;

import java.util.Map;

/**
 * @author nguyenle
 * @since 9:01 PM Mon 10/7/2024
 */
public interface ProductService {

    ValidateCartResponse validate(Map<Long, Integer> products);

}
