package com.myproject.cart_service.repository;

import com.myproject.cart_service.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author nguyenle
 */
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findByUserIdAndState(String userId, int state);

}
