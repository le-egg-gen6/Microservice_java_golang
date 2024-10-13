package com.myproject.payment_service.kafka.events;

import com.myproject.payment_service.kafka.messages.KafkaCreatePaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 * @since 7:15 PM Sat 10/12/2024
 */
@Component
@RequiredArgsConstructor
public class EventHandleCreatePaymentMessage {

    @Async
    @EventListener
    public void handleCreatePaymentMessage(KafkaCreatePaymentMessage message) {

    }

}
