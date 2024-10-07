package com.myproject.product_service.service;

import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.entity.Category;
import com.myproject.product_service.payload.request.CreateCategoryRequest;
import com.myproject.product_service.payload.request.UpdateCategoryRequest;

import java.util.List;

/**
 * @author nguyenle
 * @since 2:31 AM Fri 9/13/2024
 */
public interface CategoryService {

    void saveAsync(Category category);

    Category getCategory(Long id);

    List<Category> getCategoryByIdIn(List<Long> ids);


    CategoryDTO createCategory(CreateCategoryRequest request);

    List<CategoryDTO> getAllCategory();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategoryInfo(Long id, UpdateCategoryRequest request);

    List<CategoryDTO> getProductCategories(Long productId);

}
