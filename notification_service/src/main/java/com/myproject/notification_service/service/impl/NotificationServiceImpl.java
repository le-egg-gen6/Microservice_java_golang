package com.myproject.notification_service.service.impl;

import com.myproject.notification_service.config.security.SecurityContextService;
import com.myproject.notification_service.config.security.UserSimpleDetails;
import com.myproject.notification_service.entity.Notification;
import com.myproject.notification_service.exception.AuthenticationException;
import com.myproject.notification_service.payload.NotificationResponse;
import com.myproject.notification_service.repository.NotificationRepository;
import com.myproject.notification_service.service.NotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:59 PM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final SecurityContextService securityContextService;

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponse> getPageNotification(int page, int pageSize) {
        UserSimpleDetails userSimpleDetails = securityContextService.getCurrentUser();
        if (userSimpleDetails == null) {
            throw new AuthenticationException();
        }
        return getPageNotification(userSimpleDetails.getId(), page, pageSize);
    }

    @Override
    public List<NotificationResponse> getPageNotification(String userId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Notification> notificationsPage = notificationRepository.findByUserId(userId, pageable).stream().toList();
        return notificationsPage.stream().map(
            notification -> NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .type(notification.getType())
                .title(notification.getTitle())
                .content(notification.getContent())
                .read(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build()
        ).toList();
    }

}
