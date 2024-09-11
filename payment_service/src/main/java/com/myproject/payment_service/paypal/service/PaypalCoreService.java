package com.myproject.payment_service.paypal.service;

import com.paypal.api.payments.Payment;

/**
 * @author nguyenle
 * @since 1:49 AM Thu 9/12/2024
 */
public interface PaypalCoreService {

    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    );

    Payment executePayment(
            String paymentId,
            String payerId
    );

}
