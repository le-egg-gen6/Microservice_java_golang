package com.myproject.notification_service.socket.event;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.myproject.notification_service.socket.annotation.SocketEvent;
import com.myproject.notification_service.socket.message.cs.CSLogin;

/**
 * @author nguyenle
 * @since 1:49 AM Fri 9/20/2024
 */
@SocketEvent(eventName = EventConstant.LOGIN)
public class EventLogin implements DataListener<CSLogin> {

    @Override
    public void onData(
            SocketIOClient client,
            CSLogin data,
            AckRequest ackRequest
    ) throws Exception {

    }

}
