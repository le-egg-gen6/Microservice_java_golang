package com.myproject.payment_service.vnpay.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 10:04 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPAYOrderRequestDTO {

    private Long amount;

    private String orderInfo;

}
