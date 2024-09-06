package com.myproject.cart_service.repository;

import com.myproject.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
