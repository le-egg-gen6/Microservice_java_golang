package com.myproject.payment_service.vnpay.service.impl;

import com.myproject.payment_service.vnpay.config.VnpayConfig;
import com.myproject.payment_service.vnpay.dto.VNPAYCreatePaymentRequest;
import com.myproject.payment_service.vnpay.service.VnpayCoreService;
import com.myproject.payment_service.vnpay.utils.VnpayUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 7:20 AM Thu 9/12/2024
 */
@Service
@RequiredArgsConstructor
public class VnpayCoreServiceImpl implements VnpayCoreService {

    private final VnpayConfig vnpayConfig;

    private final VnpayUtils vnpayUtils;


    @Override
    public VNPAYCreatePaymentRequest createPayment() {
        return null;
    }

    @Override
    public VNPAYCreatePaymentRequest executePayment() {
        return null;
    }
}
