package com.myproject.notification_service.socket.event;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.myproject.notification_service.socket.annotation.SocketEvent;
import com.myproject.notification_service.socket.message.cs.CSChatMessage;

/**
 * @author nguyenle
 * @since 2:12 AM Thu 9/19/2024
 */
@SocketEvent(eventName = EventConstant.CHAT_MESSAGE)
public class EventChatMessage implements DataListener<CSChatMessage> {

    @Override
    public void onData(
            SocketIOClient client,
            CSChatMessage data,
            AckRequest ackRequest
    ) throws Exception {

    }

}
