package com.myproject.product_service.mapper;

import com.myproject.product_service.dto.CategoryDTO;
import com.myproject.product_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author nguyenle
 * @since 1:06 AM Sat 9/21/2024
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Named("categoryToDTO")
    CategoryDTO categoryToCategoryDTO(Category category);

    @Named("categoryToEntity")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories);
    List<Category> categoryDTOsToCategories(List<CategoryDTO> categoryDTOs);
}
