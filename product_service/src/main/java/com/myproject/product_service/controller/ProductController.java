package com.myproject.product_service.controller;

import com.myproject.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

}
