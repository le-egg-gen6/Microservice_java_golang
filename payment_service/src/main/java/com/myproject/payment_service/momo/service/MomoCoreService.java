package com.myproject.payment_service.momo.service;

import com.myproject.payment_service.momo.dto.MomoOrderRequestDTO;

import java.util.Map;

/**
 * @author nguyenle
 * @since 5:12 PM Sun 10/13/2024
 */
public interface MomoCoreService {

    Map<String, Object> createOrder(MomoOrderRequestDTO orderRequest);

    Map<String, Object> getOrderStatus(MomoOrderRequestDTO requestDTO);

}
