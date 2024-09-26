package com.myproject.product_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.entity.Category;
import com.myproject.product_service.exception.ItemNotFoundException;
import com.myproject.product_service.mapper.CategoryMapper;
import com.myproject.product_service.payload.request.CreateCategoryRequest;
import com.myproject.product_service.payload.request.UpdateCategoryRequest;
import com.myproject.product_service.repository.CategoryRepository;
import com.myproject.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 * @since 2:31 AM Fri 9/13/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private Cache<Long, Category> code2Category = Caffeine.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();


    @Override
    public CategoryDTO createCategory(CreateCategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        category = categoryRepository.save(category);
        code2Category.put(category.getId(), category);
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.getAllCategory();
        return categories.stream().map(
                category -> {
                    code2Category.put(category.getId(), category);
                    return categoryMapper.categoryToCategoryDTO(category);
                }
        ).toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = code2Category.getIfPresent(id);
        if (category == null) {
            category = categoryRepository.findById(id).orElse(null);
            if (category != null) {
                code2Category.put(id, category);
            } else {
                throw new ItemNotFoundException();
            }
        }
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategoryInfo(Long id, UpdateCategoryRequest request) {
        Category category = code2Category.getIfPresent(id);
        if (category == null) {
            category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                throw new ItemNotFoundException();
            }
        }
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        code2Category.put(id, category);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDTO(category);
    }
}
