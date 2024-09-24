package com.myproject.notification_service.socket.manager;

import com.corundumstudio.socketio.SocketIOClient;
import com.myproject.notification_service.utils.SpringContextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nguyenle
 * @since 3:00 AM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountManager {

    public static AccountManager getInstance() {
        return SpringContextUtils.getSingleton(AccountManager.class);
    }

    private final Map<Long, SocketIOClient> mapCode2Client = new HashMap<>();

    public void add(Long code, SocketIOClient client) {
        if (mapCode2Client.containsKey(code)) {
            SocketIOClient registeredClient = mapCode2Client.get(code);
            if (!registeredClient.getSessionId().toString().equals(client.getSessionId().toString())) {
                registeredClient.disconnect();
            }
        }
        mapCode2Client.put(code, client);
        log.info(String.format("Client with code : %d, sessionId: %s connected!", code, client.getSessionId().toString()));
        log.info(String.format("Current client online: %d", mapCode2Client.size()));
    }

    public void remove(Long code) {
        if (mapCode2Client.containsKey(code)) {
            mapCode2Client.remove(code);
            log.info(String.format("Client with code : %d, disconnected!", code));
            log.info(String.format("Current client online: %d", mapCode2Client.size()));
        }
    }

    public boolean isOnline(Long code) {
        return mapCode2Client.containsKey(code);
    }

}
