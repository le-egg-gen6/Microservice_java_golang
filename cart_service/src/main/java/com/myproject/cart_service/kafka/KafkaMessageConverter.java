package com.myproject.cart_service.kafka;

import com.myproject.cart_service.entity.Cart;
import com.myproject.cart_service.kafka.messages.KafkaCreatePaymentMessage;
import com.myproject.cart_service.kafka.messages.KafkaPaymentResponseMessage;
import lombok.experimental.UtilityClass;

/**
 * @author nguyenle
 * @since 2:51 PM Sat 10/12/2024
 */
@UtilityClass
public class KafkaMessageConverter {

    public static KafkaCreatePaymentMessage createPaymentMessage(Cart cart) {
        return null;
    }

    public static Cart convertPaymentResponse(KafkaPaymentResponseMessage message) {
        return null;
    }

}
