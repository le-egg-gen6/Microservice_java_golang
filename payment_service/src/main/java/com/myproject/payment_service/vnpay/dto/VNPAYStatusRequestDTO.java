package com.myproject.payment_service.vnpay.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 10:05 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPAYStatusRequestDTO {

    private String orderId;

    private String orderInfo;

    private String transactionNo;

    private String transDate;

    private Long amount;

}
