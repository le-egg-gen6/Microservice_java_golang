package com.myproject.notification_service.repository;

import com.myproject.notification_service.entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 3:57 PM Sat 9/21/2024
 */
@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
