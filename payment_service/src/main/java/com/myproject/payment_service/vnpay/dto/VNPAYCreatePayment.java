package com.myproject.payment_service.vnpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 1:15 AM Fri 9/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPAYCreatePayment {

    @JsonProperty("vnp_Version")
    private String version;

    @JsonProperty("vnp_Command")
    private String command;

    @JsonProperty("vnp_TmnCode")
    private String tmnCode;

    @JsonProperty("vnp_CurrCode")
    private String currency;

    @JsonProperty("vnp_TxnRef")
    private String transactionRefCode;

    @JsonProperty("vnp_OrderInfo")
    private String orderInfo;

    @JsonProperty("vnp_OrderType")
    private String orderType;

    @JsonProperty("vnp_Locale")
    private String locale;

    @JsonProperty("vnp_ReturnUrl")
    private String returnUrl;

    @JsonProperty("vnp_IpAddr")
    private String ipAddress;

    @JsonProperty("vnp_CreateDate")
    private String createDate;

    @JsonProperty("vnp_ExpireDate")
    private String expireDate;

    @JsonProperty("redirect_url")
    private String redirectUrl;
}
