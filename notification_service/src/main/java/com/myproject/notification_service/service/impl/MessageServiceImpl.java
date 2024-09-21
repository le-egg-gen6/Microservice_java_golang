package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.repository.MessageRepository;
import com.myproject.notification_service.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:58 PM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

}
