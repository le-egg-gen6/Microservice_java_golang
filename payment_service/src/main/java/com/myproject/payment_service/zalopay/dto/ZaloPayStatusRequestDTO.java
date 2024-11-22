package com.myproject.payment_service.zalopay.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 4:43 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZaloPayStatusRequestDTO {

    private String orderId;

    private String orderInfo;

    private String transactionNo;

    private String transDate;

    private Long amount;

}
