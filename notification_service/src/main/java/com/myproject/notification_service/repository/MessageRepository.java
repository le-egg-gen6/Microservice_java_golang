package com.myproject.notification_service.repository;

import com.myproject.notification_service.entity.Message;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 3:55 PM Sat 9/21/2024
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

	Optional<Message> findById(String id);

	@Query(value = "{'conversation_id': ?0}", sort = "{'created_at': -1}")
	Page<Message> findByConversationId(String conversationId, Pageable pageable);

}
