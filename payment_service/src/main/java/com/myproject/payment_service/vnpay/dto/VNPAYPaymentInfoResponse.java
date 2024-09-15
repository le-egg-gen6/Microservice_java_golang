package com.myproject.payment_service.vnpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 2:06 AM Fri 9/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPAYPaymentInfoResponse {

    @JsonProperty("vnp_ResponseId")
    @NonNull
    private String responseId;

    @JsonProperty("vnp_Command")
    @NonNull
    private String command;

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

    @JsonProperty("vnp_Message")
    @NonNull
    private String message;

    @JsonProperty("vnp_BankCode")
    @NonNull
    private String bankCode;

    @JsonProperty("vnp_CardType")
    private String cardType;

    @JsonProperty("vnp_PayDate")
    @NonNull
    private String payDate;

    @JsonProperty("vnp_TransactionNo")
    @NonNull
    private String transactionNo;

    @JsonProperty("vnp_TransactionType")
    @NonNull
    private String transactionType;

    @JsonProperty("vnp_TransactionStatus")
    @NonNull
    private String transactionStatus;

    @JsonProperty("vnp_PromotionCode")
    private String promotionCode;

    @JsonProperty("vnp_PromotionAmount")
    private String promotionAmount;

    @JsonProperty("vnp_SecureHash")
    private String secureHash;

}
