package com.myproject.notification_service.controller;

import com.myproject.notification_service.payload.ApiResponse;
import com.myproject.notification_service.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 2:27 AM Mon 9/23/2024
 */
@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ApiResponse<?>> getAllConversation() {
        return ResponseEntity.ok(ApiResponse.successResponse(conversationService.getAllConversation()));
    }

}
