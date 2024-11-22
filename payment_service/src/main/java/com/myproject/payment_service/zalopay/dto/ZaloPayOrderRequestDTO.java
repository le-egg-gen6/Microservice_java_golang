package com.myproject.payment_service.zalopay.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 4:42 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZaloPayOrderRequestDTO {

    private String appUser;

    private Long amount;

    private Long orderId;

}
