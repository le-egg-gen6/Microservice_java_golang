package com.myproject.payment_service.zalopay.dto;

import lombok.*;

/**
 * @author nguyenle
 * @since 4:45 PM Sun 10/13/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZaloPayCallbackPaymentDTO {

    private String jsonString;

}
