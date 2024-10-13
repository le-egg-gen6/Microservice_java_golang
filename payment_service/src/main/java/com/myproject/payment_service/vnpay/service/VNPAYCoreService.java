package com.myproject.payment_service.vnpay.service;

import com.myproject.payment_service.vnpay.dto.VNPAYOrderRequestDTO;
import com.myproject.payment_service.vnpay.dto.VNPAYStatusRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @author nguyenle
 * @since 7:19 AM Thu 9/12/2024
 */
public interface VNPAYCoreService {

    Map<String, Object> createOrder(HttpServletRequest request, VNPAYOrderRequestDTO orderRequestDTO);

    Map<String, Object> getOrderStatus(HttpServletRequest request, VNPAYStatusRequestDTO statusRequestDTO)

}
