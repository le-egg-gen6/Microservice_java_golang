package com.myproject.notification_service.socket.event;

import lombok.Getter;

/**
 * @author nguyenle
 * @since 2:14 AM Thu 9/19/2024
 */
@Getter
public enum EventConstant {

    LOGIN("login"),

    CHAT_MESSAGE("chat_message"),

    NOTIFICATION("notification")
    ;

    private final String name;

    EventConstant(String name) {
        this.name = name;
    }
}
