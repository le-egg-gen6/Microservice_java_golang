package com.myproject.notification_service.socket.message.sc;

import com.myproject.notification_service.socket.config.EventConstant;
import com.myproject.notification_service.socket.message.SocketMessage;

/**
 * @author nguyenle
 * @since 2:13 AM Thu 9/19/2024
 */
public class SCChatMessage extends SocketMessage {
    @Override
    public String getEvent() {
        return EventConstant.SC_CHAT_MESSAGE;
    }
}
