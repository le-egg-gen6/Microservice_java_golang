package com.myproject.product_service.repository;

import com.myproject.product_service.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author nguyenle
 * @since 2:30 AM Fri 9/13/2024
 */
@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Long>,
        PagingAndSortingRepository<Category, Long>,
        ListPagingAndSortingRepository<Category, Long>
{
    // Lấy ra các Category của một Product cho trước
    @Query("SELECT c FROM Category c JOIN c.products p WHERE p.id = :productId")
    List<Category> findCategoriesByProductId(@Param("productId") Long productId);

    // Tìm kiếm Category qua tên (chính xác)
    Optional<Category> findByName(String name);

    // Tìm kiếm Category qua tên (gần đúng, không phân biệt chữ hoa/thường)
    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Category> findByNameContainingIgnoreCase(@Param("name") String name);

    // Tìm kiếm Category qua tên (gần đúng, không phân biệt chữ hoa/thường) với phân trang
    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Category> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
