package com.myproject.notification_service.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.myproject.notification_service.socket.annotation.SocketEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

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
        Reflections reflections = new Reflections("com.myproject", new SubTypesScanner(), new TypeAnnotationsScanner());

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(SocketEvent.class);
        for (Class<?> clazz : annotatedClasses) {
            if (DataListener.class.isAssignableFrom(clazz)) {
                SocketEvent annotation = clazz.getAnnotation(SocketEvent.class);
                String eventName = annotation.eventName();

                Type[] interfaces = clazz.getGenericInterfaces();
                for (Type type : interfaces) {
                    if (type instanceof ParameterizedType parameterizedType) {
                        if (parameterizedType.getRawType().equals(DataListener.class)) {
                            Class<?> dataType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                            try {
                                Object listener = clazz.getDeclaredConstructor().newInstance();
                                registerEventListener(eventName, dataType, listener);
                            } catch (Exception e) {
                                log.error("Error creating instance of " + clazz.getName(), e);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private <T> void registerEventListener(String eventName, Class<T> dataType, Object listenerObj) {
        if (!(listenerObj instanceof DataListener)) {
            log.error("Listener object is not an instance of DataListener: " + listenerObj.getClass().getName());
            return;
        }

        @SuppressWarnings("unchecked")
        DataListener<T> typedListener = (DataListener<T>) listenerObj;

        socketIOServer.addEventListener(eventName, dataType, (client, data, ackRequest) -> {
            try {
                typedListener.onData(client, data, ackRequest);
            } catch (Exception e) {
                log.error("Error in socket event listener: " + eventName, e);
            }
        });
        log.info("Registered socket event listener for: " + eventName);
    }

}
