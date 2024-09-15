package com.myproject.payment_service.vnpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author nguyenle
 * @since 1:24 AM Mon 9/16/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPAYGetPaymentInfoRequest {

    @JsonProperty("vnp_RequestId")
    @NonNull
    private String requestId;

    @JsonProperty("vnp_Version")
    @NonNull
    private String version;

    @JsonProperty("vnp_Command")
    @NonNull
    private String command;

    @JsonProperty("vnp_TmnCode")
    @NonNull
    private String tmnCode;

    @JsonProperty("vnp_TxnRef")
    @NonNull
    private String transactionRefCode;

    @JsonProperty("vnp_OrderInfo")
    @NonNull
    private String orderInfo;

    @JsonProperty("vnp_TransactionNo")
    private String transactionNo;

    @JsonProperty("vnp_TransactionDate")
    @NonNull
    private String transactionDate;

    @JsonProperty("vnp_CreateDate")
    @NonNull
    private String createDate;

    @JsonProperty("vnp_IpAddr")
    @NonNull
    private String ipAddress;

    @JsonProperty("vnp_SecureHash")
    @NonNull
    private String secureHash;
}
