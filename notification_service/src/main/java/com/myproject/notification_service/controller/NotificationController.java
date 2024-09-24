package com.myproject.notification_service.controller;

import com.myproject.notification_service.payload.ApiResponse;
import com.myproject.notification_service.payload.NotificationResponse;
import com.myproject.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nguyenle
 * @since 2:46 AM Mon 9/23/2024
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<ApiResponse<?>> getPageNotification(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ) {
        List<NotificationResponse> notificationResponseList = notificationService.getPageNotification(page, pageSize);
        return ResponseEntity.ok(ApiResponse.successResponse(notificationResponseList));
    }

}
