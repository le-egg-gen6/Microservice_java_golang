package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.config.security.SecurityContextService;
import com.myproject.notification_service.config.security.UserSimpleDetails;
import com.myproject.notification_service.entity.Conversation;
import com.myproject.notification_service.entity.Message;
import com.myproject.notification_service.exception.AuthenticationException;
import com.myproject.notification_service.payload.MessageResponse;
import com.myproject.notification_service.repository.ConversationRepository;
import com.myproject.notification_service.repository.MessageRepository;
import com.myproject.notification_service.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:58 PM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final SecurityContextService securityContextService;

    private final MessageRepository messageRepository;

    private final ConversationRepository conversationRepository;

    @Override
    public List<MessageResponse> getPageMessage(String conversationId, int page, int pageSize) {
        UserSimpleDetails userSimpleDetails = securityContextService.getCurrentUser();
        if (userSimpleDetails == null) {
            throw new AuthenticationException();
        }
        Conversation conversation = conversationRepository.findById(conversationId).orElse(null);
        if (conversation == null) {
            return List.of();
        }
        if (!conversation.getParticipantIds().contains(userSimpleDetails.getId())) {
            throw new AuthenticationException();
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Message> messagesPage = messageRepository.findByConversationId(conversationId, pageable).stream().toList();
        return messagesPage.stream().map(
            message -> MessageResponse.builder()
                .id(message.getId())
                .conversationId(message.getConversationId())
                .senderId(message.getSenderId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build()
        ).toList();
    }
}
