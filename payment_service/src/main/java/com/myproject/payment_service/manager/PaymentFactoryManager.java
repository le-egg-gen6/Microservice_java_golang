package com.myproject.payment_service.manager;

import com.myproject.payment_service.momo.service.MomoCoreService;
import com.myproject.payment_service.paypal.service.PaypalCoreService;
import com.myproject.payment_service.vnpay.service.VNPAYCoreService;
import com.myproject.payment_service.zalopay.service.ZaloPayCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 * @since 5:11 PM Sun 10/13/2024
 */
@Component
@RequiredArgsConstructor
public class PaymentFactoryManager {

    private final PaypalCoreService paypalCoreService;

    private final VNPAYCoreService vnpayCoreService;

    private final ZaloPayCoreService zaloPayCoreService;

    private final MomoCoreService momoCoreService;

}
