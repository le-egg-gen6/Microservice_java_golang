package com.myproject.notification_service.service;

import com.myproject.notification_service.payload.ConversationResponse;
import java.util.List;

/**
 * @author nguyenle
 * @since 3:59 PM Sat 9/21/2024
 */
public interface ConversationService {

	List<ConversationResponse> getAllConversation();

	List<ConversationResponse> getAllConversationByUserId(String id);

}
