package com.myproject.notification_service.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author nguyenle
 * @since 2:23 AM Thu 9/19/2024
 */
@Configuration
public class SocketServerConfig {

    @Value("${socket-server.host}")
    private String socketServerHost;

    @Value("${socket-server.port}")
    private Integer socketServerPort;

    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(socketServerHost);
        configuration.setPort(socketServerPort);

        return new SocketIOServer(configuration);
    }

}
