package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.repository.NotificationRepository;
import com.myproject.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:59 PM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

}
