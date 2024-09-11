package com.myproject.payment_service.paypal.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nguyenle
 * @since 1:25 AM Thu 9/12/2024
 */
@Configuration
public class PaypalConfig {

    @Value("${paypal.client-id}")
    private String paypalClientId;

    @Value("${paypal.client-secret}")
    private String paypalClientSecret;

    @Value("${paypal.mode}")
    private String paypalMode;

    private Map<String, String> paypalClientConfig() {
        Map<String, String> configMap = new HashMap<>();
        return configMap;
    }

    @Bean
    public APIContext getApiContext() throws PayPalRESTException {
        return new APIContext(
                paypalClientId,
                paypalClientSecret,
                paypalMode,
                paypalClientConfig()
        );
    }

}
