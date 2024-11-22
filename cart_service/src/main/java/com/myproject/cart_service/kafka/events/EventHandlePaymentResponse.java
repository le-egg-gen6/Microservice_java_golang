package com.myproject.cart_service.kafka.events;

import com.myproject.cart_service.kafka.messages.KafkaPaymentResponseMessage;
import com.myproject.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 * @since 6:51 PM Sat 10/12/2024
 */
@Component
@RequiredArgsConstructor
public class EventHandlePaymentResponse {

    private final CartService cartService;

    @Async
    @EventListener
    public void handlePaymentResponseMessage(KafkaPaymentResponseMessage message) {

    }

}
