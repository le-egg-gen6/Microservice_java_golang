package com.myproject.product_service.controller.secured;

import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.payload.request.CreateCategoryRequest;
import com.myproject.product_service.payload.response.CategoryResponse;
import com.myproject.product_service.payload.shared.ApiResponse;
import com.myproject.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author nguyenle
 * @since 1:44 AM Mon 9/23/2024
 */
@RestController
@RequestMapping("/secured/category")
@RequiredArgsConstructor
public class SecuredCategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<?>> createNewCategory(
            @RequestBody CreateCategoryRequest request
    ) {
        CategoryDTO categoryDTO = categoryService.createCategory(request);
        CategoryResponse response = CategoryResponse.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
        return ResponseEntity.ok(ApiResponse.successResponse(response));
    }

}
