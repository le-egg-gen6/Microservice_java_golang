package com.myproject.product_service.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 */
@Component
public class SecurityConstant {

    @Value("${internal.secret}")
    private String INTERNAL_SECRET;

    private String[] PUBLIC_URLS = {
            "/public/**"
    };

    public String getInternalSecret() {
        return INTERNAL_SECRET;
    }

    public String[] getPublicUrls() {
        return PUBLIC_URLS;
    }

}
