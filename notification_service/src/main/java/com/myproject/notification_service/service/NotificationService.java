package com.myproject.notification_service.service;

import com.myproject.notification_service.payload.NotificationResponse;
import java.util.List;

/**
 * @author nguyenle
 * @since 3:58 PM Sat 9/21/2024
 */
public interface NotificationService {

	List<NotificationResponse> getPageNotification(int page, int pageSize);

	List<NotificationResponse> getPageNotification(String userId, int page, int pageSize);

}
