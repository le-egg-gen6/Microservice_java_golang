package com.myproject.product_service.config.security;

import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 */
@Component
public class SecurityConstant {

    private String[] PUBLIC_URLS = {
            "/public/**"
    };

    public String[] getPublicUrls() {
        return PUBLIC_URLS;
    }

}
