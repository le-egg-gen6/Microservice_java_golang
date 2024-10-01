package com.myproject.notification_service.socket.manager;

import com.myproject.notification_service.utils.SpringContextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 3:48 AM Sat 9/21/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageManager {

    private final AccountManager accountManager;

    public static ChatMessageManager getInstance() {
        return SpringContextUtils.getSingleton(ChatMessageManager.class);
    }

}
