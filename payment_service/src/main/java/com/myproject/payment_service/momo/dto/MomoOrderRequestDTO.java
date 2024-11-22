package com.myproject.payment_service.momo.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 9:52 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomoOrderRequestDTO {

    private Long amount;

    private String orderId;

    private String orderInfo;

    private String lang;

    private String extraData;

}
