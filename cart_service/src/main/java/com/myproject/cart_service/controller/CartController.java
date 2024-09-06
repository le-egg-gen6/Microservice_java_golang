package com.myproject.cart_service.controller;

import com.myproject.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 */
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

}
