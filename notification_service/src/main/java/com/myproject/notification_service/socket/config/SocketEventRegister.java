package com.myproject.notification_service.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.myproject.notification_service.socket.annotation.SocketEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author nguyenle
 * @since 3:26 AM Sat 9/21/2024
 */
@Configuration
@Order(value = Ordered.LOWEST_PRECEDENCE)
@RequiredArgsConstructor
@Slf4j
public class SocketEventRegister {

    private final SocketIOServer socketIOServer;

    private final ApplicationContext applicationContext;

    @PostConstruct
    public void registerEvents() {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(beanName);
            Class<?> beanClass = bean.getClass();
            if (beanClass.isAnnotationPresent(SocketEvent.class)) {
                SocketEvent annotation = beanClass.getAnnotation(SocketEvent.class);
                String eventName = annotation.eventName();

                if (bean instanceof DataListener) {
                    Type[] interfaces = beanClass.getGenericInterfaces();
                    for (Type type : interfaces) {
                        if (type instanceof ParameterizedType parameterizedType) {
                            if (parameterizedType.getRawType().equals(DataListener.class)) {
                                Class<?> dataType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                                registerEventListener(eventName, dataType, (DataListener<?>) bean);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void registerEventListener(String eventName, Class<?> dataType, DataListener<?> listener) {
        socketIOServer.addEventListener(eventName, dataType, (client, data, ackRequest) -> {
            try {
                listener.onData(client, data, ackRequest);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }

}
