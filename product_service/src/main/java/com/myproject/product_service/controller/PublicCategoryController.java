package com.myproject.product_service.controller;

import com.myproject.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 6:18 AM Tue 9/17/2024
 */
@RestController
@RequiredArgsConstructor
public class PublicCategoryController {

    private final CategoryService categoryService;

}
