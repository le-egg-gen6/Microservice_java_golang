package com.myproject.product_service.repository;

import com.myproject.product_service.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 */
@Repository
public interface PromotionRepository extends
        JpaRepository<Promotion, Long>,
        PagingAndSortingRepository<Promotion, Long>,
        ListPagingAndSortingRepository<Promotion, Long>
{

}
