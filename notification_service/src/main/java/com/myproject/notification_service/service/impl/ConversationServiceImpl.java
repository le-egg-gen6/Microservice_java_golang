package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.config.security.SecurityContextService;
import com.myproject.notification_service.config.security.UserSimpleDetails;
import com.myproject.notification_service.entity.Conversation;
import com.myproject.notification_service.payload.ConversationResponse;
import com.myproject.notification_service.repository.ConversationRepository;
import com.myproject.notification_service.service.ConversationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:59 PM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final SecurityContextService securityContextService;

    private final ConversationRepository conversationRepository;

    @Override
    public List<ConversationResponse> getAllConversation() {
        UserSimpleDetails userSimpleDetails = securityContextService.getCurrentUser();
        if (userSimpleDetails == null) {
            throw new Exception();
        }
        return getAllConversationByUserId(userSimpleDetails.getId());
    }

    @Override
    public List<ConversationResponse> getAllConversationByUserId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return List.of();
        }
        List<Conversation> conversations = conversationRepository.findByParticipantId(id);
        return conversations.stream().map(
            conversation -> {
                return ConversationResponse.builder()
                    .build();
            }
        ).toList();
    }
}
