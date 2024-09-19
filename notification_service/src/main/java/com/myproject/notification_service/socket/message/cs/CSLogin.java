package com.myproject.notification_service.socket.message.cs;

import com.myproject.notification_service.socket.event.EventConstant;
import com.myproject.notification_service.socket.message.SocketMessage;

/**
 * @author nguyenle
 * @since 2:13 AM Thu 9/19/2024
 */
public class CSLogin extends SocketMessage {


    @Override
    public String getEvent() {
        return EventConstant.LOGIN.getName();
    }
}
