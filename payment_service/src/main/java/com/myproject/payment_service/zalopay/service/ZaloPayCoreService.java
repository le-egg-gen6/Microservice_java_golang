package com.myproject.payment_service.zalopay.service;

import com.myproject.payment_service.zalopay.dto.ZaloPayOrderRequestDTO;
import com.myproject.payment_service.zalopay.dto.ZaloPayStatusRequestDTO;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author nguyenle
 * @since 4:46 PM Sun 10/13/2024
 */
public interface ZaloPayCoreService {

    Map<String, String> createOrder(ZaloPayOrderRequestDTO orderRequestDTO);

    Map<String, Object> statusOrder(ZaloPayStatusRequestDTO statusRequestDTO);

    Object doCallback(JSONObject result, String jsonStr);

}
