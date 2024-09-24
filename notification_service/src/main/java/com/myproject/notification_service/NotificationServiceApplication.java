package com.myproject.notification_service;

import com.myproject.notification_service.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringContextUtils.startSpringApplication(NotificationServiceApplication.class, args);
    }

}
