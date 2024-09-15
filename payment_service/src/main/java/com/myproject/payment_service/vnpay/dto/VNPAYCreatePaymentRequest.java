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
public class VNPAYCreatePaymentRequest {

    @JsonProperty("vnp_Version")
    @NonNull
    private String version;

    @JsonProperty("vnp_Command")
    @NonNull
    private String command;

    @JsonProperty("vnp_TmnCode")
    @NonNull
    private String tmnCode;

    @JsonProperty("vnp_BankCode")
    private String bankCode;

    @JsonProperty("vnp_Locale")
    @NonNull
    private String locale;

    @JsonProperty("vnp_CurrCode")
    @NonNull
    private String currency;

    @JsonProperty("vnp_TxnRef")
    @NonNull
    private String transactionRefCode;

    @JsonProperty("vnp_OrderInfo")
    @NonNull
    private String orderInfo;

    @JsonProperty("vnp_OrderType")
    @NonNull
    private String orderType;

    @JsonProperty("vnp_Amount")
    @NonNull
    private String amount;

    @JsonProperty("vnp_ReturnUrl")
    @NonNull
    private String returnUrl;

    @JsonProperty("vnp_IpAddr")
    @NonNull
    private String ipAddress;

    @JsonProperty("vnp_CreateDate")
    @NonNull
    private String createDate;

    @JsonProperty("vnp_ExpireDate")
    @NonNull
    private String expireDate;

    @JsonProperty("vnp_Bill_Mobile")
    private String billMobile;

    @JsonProperty("vnp_Bill_Email")
    private String billEmail;

    @JsonProperty("vnp_Bill_FirstName")
    private String billFirstName;

    @JsonProperty("vnp_Bill_LastName")
    private String billLastName;

    @JsonProperty("vnp_Bill_Address")
    private String billAddress;

    @JsonProperty("vnp_Bill_City")
    private String billCity;

    @JsonProperty("vnp_Bill_Country")
    private String billCountry;

    @JsonProperty("vnp_Bill_State")
    private String billState;

    @JsonProperty("vnp_Inv_phone")
    private String invPhone;

    @JsonProperty("vnp_Inv_Email")
    private String invEmail;

    @JsonProperty("vnp_Inv_Customer")
    private String invCustomer;

    @JsonProperty("vnp_Inv_Address")
    private String invAddress;

    @JsonProperty("vnp_Inv_Company")
    private String invCompany;

    @JsonProperty("vnp_Inv_Taxcode")
    private String invTaxcode;

    @JsonProperty("vnp_Inv_Type")
    private String invType;

    @JsonProperty("vnp_SecureHash")
    @NonNull
    private String secureHash;
}
