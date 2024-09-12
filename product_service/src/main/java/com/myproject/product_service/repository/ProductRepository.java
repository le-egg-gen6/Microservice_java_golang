package com.myproject.product_service.repository;

import com.myproject.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
