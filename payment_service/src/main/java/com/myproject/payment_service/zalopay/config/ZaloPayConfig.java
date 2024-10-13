package com.myproject.payment_service.zalopay.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author nguyenle
 * @since 4:35 PM Sun 10/13/2024
 */
@Configuration
public class ZaloPayConfig {

    private String APP_ID = "2554";
    private String KEY_1 = "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn";
    private String KEY_2 = "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz";
    public final String ORDER_CREATE_ENDPOINT
            = "https://sandbox.zalopay.com.vn/v001/tpe/createorder";

    public final String ORDER_STATUS_ENDPOINT
            = "https://sandbox.zalopay.com.vn/v001/tpe/getstatusbyapptransid";

    public String getAppId() {
        return APP_ID;
    }

    public String getKey1() {
        return KEY_1;
    }

    public String getKey2() {
        return KEY_2;
    }

    public String getOrderCreateEndpoint() {
        return ORDER_CREATE_ENDPOINT;
    }

    public String getOrderStatusEndpoint() {
        return ORDER_STATUS_ENDPOINT;
    }

}
