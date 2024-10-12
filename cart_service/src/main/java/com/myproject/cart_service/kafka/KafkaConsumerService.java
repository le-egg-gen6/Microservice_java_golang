package com.myproject.cart_service.kafka;

import com.myproject.cart_service.kafka.messages.KafkaPaymentResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(
            topics = "payment_response",
            groupId = "_"
    )
    public void listenPaymentResponseMessage(KafkaPaymentResponseMessage message) {
        eventPublisher.publishEvent(message);
    }



}
