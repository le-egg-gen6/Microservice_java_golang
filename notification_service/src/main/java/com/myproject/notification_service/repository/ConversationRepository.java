package com.myproject.notification_service.repository;

import com.myproject.notification_service.entity.Conversation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 3:57 PM Sat 9/21/2024
 */
@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {

	Optional<Conversation> findById(String id);

	// Find all conversations where the given userId is a participant, sorted by modified date desc
	@Query(value = "{'participant_ids': ?0}", sort = "{'modified_at': -1}")
	List<Conversation> findByParticipantId(String userId);

	// Paginated query with default sorting
	@Query(value = "{'participant_ids': ?0}", sort = "{'modified_at': -1}")
	Page<Conversation> findByParticipantId(String userId, Pageable pageable);

}
