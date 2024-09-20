package com.myproject.notification_service.socket.message.sc;

import com.myproject.notification_service.socket.event.EventConstant;
import com.myproject.notification_service.socket.message.SocketMessage;

/**
 * @author nguyenle
 * @since 1:49 AM Fri 9/20/2024
 */
public class SCLogin extends SocketMessage {

    @Override
    public String getEvent() {
        return EventConstant.LOGIN;
    }
}
