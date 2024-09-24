package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.config.security.SecurityContextService;
import com.myproject.notification_service.config.security.UserSimpleDetails;
import com.myproject.notification_service.entity.Conversation;
import com.myproject.notification_service.entity.Message;
import com.myproject.notification_service.exception.AuthenticationException;
import com.myproject.notification_service.payload.ConversationResponse;
import com.myproject.notification_service.payload.MessageResponse;
import com.myproject.notification_service.repository.ConversationRepository;
import com.myproject.notification_service.repository.MessageRepository;
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

    private final MessageRepository messageRepository;

    @Override
    public List<ConversationResponse> getAllConversation() {
        UserSimpleDetails userSimpleDetails = securityContextService.getCurrentUser();
        if (userSimpleDetails == null) {
            throw new AuthenticationException();
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
                Message message = messageRepository.findById(conversation.getLastMessageId()).orElse(null);
                MessageResponse messageResponse = null;
                if (message != null) {
                    messageResponse = MessageResponse.builder()
                        .id(message.getId())
                        .conversationId(conversation.getId())
                        .senderId(message.getSenderId())
                        .content(message.getContent())
                        .createdAt(message.getCreatedAt())
                        .build();
                }
                return ConversationResponse.builder()
                    .id(conversation.getId())
                    .title(conversation.getTitle())
                    .createdAt(conversation.getCreatedAt())
                    .modifiedAt(conversation.getModifiedAt())
                    .participantIds(conversation.getParticipantIds())
                    .lastMessage(messageResponse)
                    .build();
            }
        ).toList();
    }
}
