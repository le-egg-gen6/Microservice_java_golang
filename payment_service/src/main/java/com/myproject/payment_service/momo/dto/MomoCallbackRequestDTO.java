package com.myproject.payment_service.momo.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 9:53 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomoCallbackRequestDTO {

    private Long amount;

    private String orderId;

    private String orderInfo;

}
