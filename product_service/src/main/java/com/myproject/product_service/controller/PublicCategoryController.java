package com.myproject.product_service.controller;

import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.payload.response.CategoryResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 6:18 AM Tue 9/17/2024
 */
@RestController
@RequestMapping("/public/category")
@RequiredArgsConstructor
public class PublicCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<?>> getCategoryInfo(
            @RequestParam("id") Long id
    ) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        CategoryResponse response = CategoryResponse.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }

}
