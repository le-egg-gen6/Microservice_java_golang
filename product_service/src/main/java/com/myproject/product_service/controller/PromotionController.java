package com.myproject.product_service.controller;

import com.myproject.product_service.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 6:17 AM Tue 9/17/2024
 */
@RestController
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

}
