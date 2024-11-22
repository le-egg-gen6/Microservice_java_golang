package com.myproject.notification_service.socket.manager;

import com.corundumstudio.socketio.SocketIOClient;
import com.myproject.notification_service.config.auth_service.AuthResponse;
import com.myproject.notification_service.config.auth_service.AuthService;
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

    private final Map<String, SocketIOClient> mapCode2Client = new HashMap<>();

    private final AuthService authService;

    public void add(String id, SocketIOClient client) {
        if (mapCode2Client.containsKey(id)) {
            SocketIOClient registeredClient = mapCode2Client.get(id);
            if (!registeredClient.getSessionId().toString().equals(client.getSessionId().toString())) {
                registeredClient.disconnect();
            }
        }
        mapCode2Client.put(id, client);
    }

    public void remove(String id) {
	    mapCode2Client.remove(id);
    }

    public boolean authorizedSocketRequest(String id, String token) {
        AuthResponse response = authService.validate(token);
        if (response == null) {
            return false;
        } else {
            return response.isAuthenticated() && response.getUserSimpleDetails().getId().equals(id);
        }
    }

    public boolean isOnline(String code) {
        return mapCode2Client.containsKey(code);
    }

}
