package com.myproject.notification_service.utils;

import com.corundumstudio.socketio.SocketIOClient;
import com.myproject.notification_service.socket.message.SocketMessage;
import lombok.experimental.UtilityClass;

/**
 * @author nguyenle
 * @since 5:05 PM Fri 11/22/2024
 */
@UtilityClass
public class SocketIOUtils {

	public static void send(SocketIOClient client, SocketMessage message) {
		client.sendEvent(message.getEvent(), message);
	}

}
