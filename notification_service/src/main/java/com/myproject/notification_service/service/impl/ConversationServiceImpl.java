package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.repository.ConversationRepository;
import com.myproject.notification_service.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:59 PM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

}
