package com.myproject.notification_service.repository;

import com.myproject.notification_service.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 3:55 PM Sat 9/21/2024
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
