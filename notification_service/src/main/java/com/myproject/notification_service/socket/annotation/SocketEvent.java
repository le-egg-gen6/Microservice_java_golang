package com.myproject.notification_service.socket.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author nguyenle
 * @since 3:06 AM Sat 9/21/2024
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketEvent {
    String eventName();
}
