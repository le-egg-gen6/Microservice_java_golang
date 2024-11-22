package com.myproject.notification_service.socket.message.cs;

import com.myproject.notification_service.socket.config.EventConstant;
import com.myproject.notification_service.socket.message.SocketMessage;

/**
 * @author nguyenle
 * @since 1:50 AM Fri 9/20/2024
 */
public class CSChatMessage extends SocketMessage {
    @Override
    public String getEvent() {
        return EventConstant.CS_CHAT_MESSAGE;
    }
}
