package com.myproject.notification_service.repository;

import com.myproject.notification_service.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 3:56 PM Sat 9/21/2024
 */
@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

	@Query(value = "{'user_id': ?0}", sort = "{'created_at': -1}")
	Page<Notification> findByUserId(String userId, Pageable pageable);

}
