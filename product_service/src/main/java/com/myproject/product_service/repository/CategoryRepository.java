package com.myproject.product_service.repository;

import com.myproject.product_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 2:30 AM Fri 9/13/2024
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
