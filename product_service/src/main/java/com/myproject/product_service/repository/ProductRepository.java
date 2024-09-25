package com.myproject.product_service.repository;

import com.myproject.product_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nguyenle
 */
@Repository
public interface ProductRepository extends
        JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long>,
        ListPagingAndSortingRepository<Product, Long>
{
    // Tìm sản phẩm theo category với phân trang
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.categories c WHERE c.id = :categoryId")
    Page<Product> findProductsByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    // Tìm sản phẩm theo nhiều category với phân trang
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.categories c WHERE c.id IN :categoryIds")
    Page<Product> findProductsByCategoryIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

    // Tìm sản phẩm theo promotion với phân trang
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.promotions pr WHERE pr.id = :promotionId")
    Page<Product> findProductsByPromotionId(@Param("promotionId") Long promotionId, Pageable pageable);

    // Tìm sản phẩm theo nhiều promotion với phân trang
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.promotions pr WHERE pr.id IN :promotionIds")
    Page<Product> findProductsByPromotionIds(@Param("promotionIds") List<Long> promotionIds, Pageable pageable);

    // Tìm sản phẩm theo category và promotion với phân trang
    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN FETCH p.categories c " +
            "JOIN FETCH p.promotions pr " +
            "WHERE c.id = :categoryId AND pr.id = :promotionId")
    Page<Product> findProductsByCategoryIdAndPromotionId(
            @Param("categoryId") Long categoryId,
            @Param("promotionId") Long promotionId,
            Pageable pageable);

    // Tìm sản phẩm theo tên category với phân trang (gần đúng)
    @Query("SELECT DISTINCT p FROM Product p JOIN p.categories c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    Page<Product> findProductsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    // Tìm sản phẩm theo tên promotion với phân trang (gần đúng)
    @Query("SELECT DISTINCT p FROM Product p JOIN p.promotions pr WHERE LOWER(pr.name) LIKE LOWER(CONCAT('%', :promotionName, '%'))")
    Page<Product> findProductsByPromotionName(@Param("promotionName") String promotionName, Pageable pageable);

    // Tìm sản phẩm theo tên category và tên promotion với phân trang (gần đúng)
    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN p.categories c " +
            "JOIN p.promotions pr " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%')) " +
            "AND LOWER(pr.name) LIKE LOWER(CONCAT('%', :promotionName, '%'))")
    Page<Product> findProductsByCategoryNameAndPromotionName(
            @Param("categoryName") String categoryName,
            @Param("promotionName") String promotionName,
            Pageable pageable);
}
