package com.myproject.notification_service.controller;

import com.myproject.notification_service.payload.ApiResponse;
import com.myproject.notification_service.payload.MessageResponse;
import com.myproject.notification_service.service.MessageService;
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
 * @since 2:45 AM Mon 9/23/2024
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'AMDIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<?>> getMessagePage(
            @RequestParam("conversationId") String conversationId,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ) {
        List<MessageResponse> messageResponseList = messageService.getPageMessage(conversationId, page, pageSize);
        return ResponseEntity.ok(ApiResponse.successResponse(messageResponseList));
    }

}
