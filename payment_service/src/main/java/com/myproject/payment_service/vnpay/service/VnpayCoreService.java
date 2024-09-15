package com.myproject.payment_service.vnpay.service;

import com.myproject.payment_service.vnpay.dto.VNPAYCreatePaymentRequest;

/**
 * @author nguyenle
 * @since 7:19 AM Thu 9/12/2024
 */
public interface VnpayCoreService {
    VNPAYCreatePaymentRequest createPayment();

    VNPAYCreatePaymentRequest executePayment();
}
