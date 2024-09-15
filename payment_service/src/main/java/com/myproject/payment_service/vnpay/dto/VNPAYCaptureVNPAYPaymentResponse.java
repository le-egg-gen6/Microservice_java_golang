package com.myproject.payment_service.vnpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 1:39 AM Mon 9/16/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPAYCaptureVNPAYPaymentResponse {

    @JsonProperty("vnp_TmnCode")
    @NonNull
    private String tmnCode;

    @JsonProperty("vnp_TxnRef")
    @NonNull
    private String transactionRefCode;

    @JsonProperty("vnp_Amount")
    @NonNull
    private String amount;

    @JsonProperty("vnp_OrderInfo")
    @NonNull
    private String orderInfo;

    @JsonProperty("vnp_ResponseCode")
    @NonNull
    private String responseCode;

    @JsonProperty("vnp_BankCode")
    @NonNull
    private String bankCode;

    @JsonProperty("vnp_BankTranNo")
    private String bankTransactionNo;

    @JsonProperty("vnp_CardType")
    private String cardType;

    @JsonProperty("vnp_PayDate")
    @NonNull
    private String payDate;

    @JsonProperty("vnp_TransactionNo")
    @NonNull
    private String transactionNo;

    @JsonProperty("vnp_TransactionStatus")
    @NonNull
    private String transactionStatus;

    @JsonProperty("vnp_SecureHash")
    private String secureHash;
}
