package com.myproject.product_service.repository;

import com.myproject.product_service.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author nguyenle
 */
@Repository
public interface PromotionRepository extends
        JpaRepository<Promotion, Long>,
        PagingAndSortingRepository<Promotion, Long>,
        ListPagingAndSortingRepository<Promotion, Long>
{

    List<Promotion> findByIdIn(List<Long> ids);

    @Query("SELECT pr FROM Promotion pr")
    Page<Promotion> getAllPromotion(Pageable pageable);

    // Lấy ra các Promotion của 1 Product cho trước
    @Query("SELECT DISTINCT pr FROM Promotion pr JOIN pr.products p WHERE p.id = :productId")
    List<Promotion> findPromotionsByProductId(@Param("productId") Long productId);

    // Lấy ra các Promotion trong khoảng từ startTime tới endTime
    @Query("SELECT pr FROM Promotion pr WHERE pr.endDate <= :endTime AND pr.startDate >= :startTime")
    Page<Promotion> findPromotionsBetweenDates(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            Pageable pageable);

    // Tìm kiếm Promotion qua tên
    @Query("SELECT pr FROM Promotion pr WHERE LOWER(pr.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Promotion> findPromotionsByNameContaining(@Param("name") String name, Pageable pageable);

    // Tìm kiếm Promotion qua id
    Page<Promotion> findPromotionById(Long id, Pageable pageable);

    // Tìm kiếm các Promotion có startDate >= time
    @Query("SELECT pr FROM Promotion pr WHERE pr.startDate >= :time")
    Page<Promotion> findPromotionsWithStartDateAfter(@Param("time") LocalDateTime time, Pageable pageable);

    // Tìm kiếm các Promotion có startDate <= time
    @Query("SELECT pr FROM Promotion pr WHERE pr.startDate <= :time")
    Page<Promotion> findPromotionsWithStartDateBefore(@Param("time") LocalDateTime time, Pageable pageable);

    // Tìm kiếm các Promotion có endDate >= time
    @Query("SELECT pr FROM Promotion pr WHERE pr.endDate >= :time")
    Page<Promotion> findPromotionsWithEndDateAfter(@Param("time") LocalDateTime time, Pageable pageable);

    // Tìm kiếm các Promotion có endDate <= time
    @Query("SELECT pr FROM Promotion pr WHERE pr.endDate <= :time")
    Page<Promotion> findPromotionsWithEndDateBefore(@Param("time") LocalDateTime time, Pageable pageable);
}
