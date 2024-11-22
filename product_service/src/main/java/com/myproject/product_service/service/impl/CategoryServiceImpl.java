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
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 * @since 2:31 AM Fri 9/13/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final int CORE_POOL_SIZE = 1;

    private final int MAX_POOL_SIZE = 5;

    private final int THREAD_LIFE_TIME_IN_MIN = 1;

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private Cache<Long, Category> code2Category;

    private ThreadPoolExecutor executors;

    @PostConstruct
    private void initialize() {
        code2Category = Caffeine.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(1_000)
                .build();

        executors = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                THREAD_LIFE_TIME_IN_MIN,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()
        );
    }

    private void addAsynchronousTask(Runnable task) {
        executors.submit(task);
    }

    @Override
    public void saveAsync(Category category) {
        addAsynchronousTask(() -> categoryRepository.save(category));
    }

    @Override
    public Category getCategory(Long id) {
        Category category = code2Category.getIfPresent(id);
        if (category == null) {
            category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                throw new ItemNotFoundException();
            }
            code2Category.put(id, category);
        }
        return category;
    }

    @Override
    public List<Category> getCategoryByIdIn(List<Long> ids) {
        return ids.stream().map(
                this::getCategory
        ).toList();
    }

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
        Category category = getCategory(id);
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategoryInfo(Long id, UpdateCategoryRequest request) {
        Category category = getCategory(id);
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        code2Category.put(id, category);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getProductCategories(Long productId) {
        List<Category> categories = categoryRepository.findCategoriesByProductId(productId);
        return categories.stream().map(
                category -> {
                    code2Category.put(category.getId(), category);
                    return categoryMapper.categoryToCategoryDTO(category);
                }
        ).toList();
    }
}
