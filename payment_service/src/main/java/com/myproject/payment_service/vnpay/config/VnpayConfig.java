package com.myproject.payment_service.vnpay.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author nguyenle
 * @since 7:20 AM Thu 9/12/2024
 */
@Configuration
@Getter
public class VnpayConfig {

    @Value("${vnpay.url}")
    private String url;

    @Value("${vnpay.api_url}")
    private String apiUrl;

    @Value("${vnpay.return_url}")
    private String returnUrl;

    @Value("${vnpay.tmn_code}")
    private String tmnCode;

    @Value("${vnpay.secret_key}")
    private String secretKey;

    @Value("${vnpay.version}")
    private String version;

    @Value("${vnpay.locale}")
    private String displayLanguage;

    @Value("${vnpay.curr_code}")
    private String currency;

}
