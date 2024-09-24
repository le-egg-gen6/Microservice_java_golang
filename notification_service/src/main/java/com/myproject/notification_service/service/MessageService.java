package com.myproject.notification_service.service;

import com.myproject.notification_service.payload.MessageResponse;
import java.util.List;

/**
 * @author nguyenle
 * @since 3:57 PM Sat 9/21/2024
 */
public interface MessageService {

	List<MessageResponse> getPageMessage(String conversationId, int page, int pageSize);

}
