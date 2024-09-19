package com.myproject.notification_service.socket.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 2:13 AM Thu 9/19/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class SocketMessage {

    public abstract String getEvent();

}
