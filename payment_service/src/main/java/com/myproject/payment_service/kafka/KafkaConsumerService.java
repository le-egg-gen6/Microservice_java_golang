package com.myproject.payment_service.kafka;

import com.myproject.payment_service.kafka.messages.KafkaCreatePaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 7:09 PM Sat 10/12/2024
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(
            topics = "payment_request",
            groupId = ""
    )
    public void listenCreatePaymentMessage(KafkaCreatePaymentMessage message) {
        eventPublisher.publishEvent(message);
    }

}
