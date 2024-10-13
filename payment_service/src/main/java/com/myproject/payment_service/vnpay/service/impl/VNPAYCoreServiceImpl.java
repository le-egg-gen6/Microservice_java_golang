package com.myproject.payment_service.vnpay.service.impl;

import com.myproject.payment_service.vnpay.config.VNPAYConfig;
import com.myproject.payment_service.vnpay.dto.VNPAYOrderRequestDTO;
import com.myproject.payment_service.vnpay.dto.VNPAYStatusRequestDTO;
import com.myproject.payment_service.vnpay.service.VNPAYCoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author nguyenle
 * @since 7:20 AM Thu 9/12/2024
 */
@Service
@RequiredArgsConstructor
public class VNPAYCoreServiceImpl implements VNPAYCoreService {

    private final VNPAYConfig vnpayConfig;

    @Override
    public Map<String, Object> createOrder(HttpServletRequest request, VNPAYOrderRequestDTO orderRequestDTO) {
        Map<String, Object> payload = new HashMap<>(){{
            put("vnp_Version", VnPayConstant.VNP_VERSION);
            put("vnp_Command", VnPayConstant.VNP_COMMAND_ORDER);
            put("vnp_TmnCode", VnPayConstant.VNP_TMN_CODE);
            put("vnp_Amount", String.valueOf(orderRequestDTO.getAmount() * 100));
            put("vnp_CurrCode", VnPayConstant.VNP_CURRENCY_CODE);
            put("vnp_TxnRef",  VnPayHelper.getRandomNumber(8));
            put("vnp_OrderInfo", orderRequestDTO.getOrderInfo());
            put("vnp_OrderType", VnPayConstant.ORDER_TYPE);
            put("vnp_Locale", VnPayConstant.VNP_LOCALE);
            put("vnp_ReturnUrl", VnPayConstant.VNP_RETURN_URL);
            put("vnp_IpAddr", VnPayHelper.getIpAddress(request));
            put("vnp_CreateDate", VnPayHelper.generateDate(false));
            put("vnp_ExpireDate", VnPayHelper.generateDate(true));
        }};

        String queryUrl = getQueryUrl(payload).get("queryUrl")
                + "&vnp_SecureHash="
                + VnPayHelper.hmacSHA512(VnPayConstant.SECRET_KEY, getQueryUrl(payload).get("hashData"));

        String paymentUrl = VnPayConstant.VNP_PAY_URL + "?" + queryUrl;
        payload.put("redirect_url", paymentUrl);

        return payload;
    }

    private Map<String, String> getQueryUrl(Map<String, Object> payload) throws UnsupportedEncodingException {

        List<String> fieldNames = new ArrayList(payload.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {

            String fieldName = (String) itr.next();
            String fieldValue = (String) payload.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {

                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {

                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        return new HashMap<>(){{
            put("queryUrl", query.toString());
            put("hashData", hashData.toString());
        }};
    }

    @Override
    public Map<String, Object> getOrderStatus(HttpServletRequest request, VNPAYStatusRequestDTO statusRequestDTO) {
        JSONObject statusQuery = new JSONObject();

        statusQuery.put("vnp_RequestId", VnPayHelper.getRandomNumber(8));
        statusQuery.put("vnp_Version", VnPayConstant.VNP_VERSION);
        statusQuery.put("vnp_Command", VnPayConstant.VNP_COMMAND_STATUS);
        statusQuery.put("vnp_TmnCode", VnPayConstant.VNP_TMN_CODE);
        statusQuery.put("vnp_TxnRef", statusRequestDTO.getOrderId());
        statusQuery.put("vnp_OrderInfo", statusRequestDTO.getOrderInfo());
        statusQuery.put("vnp_TransactionNo", statusRequestDTO.getTransactionNo());
        statusQuery.put("vnp_TransDate", statusRequestDTO.getTransDate());
        statusQuery.put("vnp_CreateDate", VnPayHelper.generateDate(false));
        statusQuery.put("vnp_IpAddr", VnPayHelper.getIpAddress(request));
        statusQuery.put("vnp_Amount", String.valueOf(statusRequestDTO.getAmount()));
        statusQuery.put("vnp_BankCode", VnPayConstant.VNP_BANK_CODE);
        statusQuery.put("vnp_ResponseCode", VnPayConstant.VNP_RESPONSE_CODE); //success status
        statusQuery.put("vnp_TransactionStatus", VnPayConstant.VNP_TRANSACTION_STATUS);

        String hashData= String.join("|"
                , statusQuery.get("vnp_RequestId").toString()
                , statusQuery.get("vnp_Version").toString()
                , statusQuery.get("vnp_Command").toString()
                , statusQuery.get("vnp_TmnCode").toString()
                , statusQuery.get("vnp_TxnRef").toString()
                , statusQuery.get("vnp_TransDate").toString()
                , statusQuery.get("vnp_TransactionNo").toString()
                , statusQuery.get("vnp_CreateDate").toString()
                , statusQuery.get("vnp_IpAddr").toString()
                , statusQuery.get("vnp_OrderInfo").toString()
                , statusQuery.get("vnp_BankCode").toString()
                , statusQuery.get("vnp_ResponseCode").toString() //success status
                , statusQuery.get("vnp_Amount").toString()
                , statusQuery.get("vnp_TransactionStatus").toString());

        String vnpSecureHash = VnPayHelper.hmacSHA512(VnPayConstant.SECRET_KEY, hashData.toString());
        statusQuery.put("vnp_SecureHash", vnpSecureHash);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(VnPayConstant.VNP_API_URL);

        StringEntity stringEntity = new StringEntity(statusQuery.toString());
        post.setHeader("content-type", "application/json");
        post.setEntity(stringEntity);

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {

            resultJsonStr.append(line);
        }

        JSONObject object = new JSONObject(resultJsonStr.toString());
        Map<String, Object> result = new HashMap<>();
        for (Iterator<String> it = object.keys(); it.hasNext(); ) {

            String key = it.next();
            result.put(key, object.get(key));
        }

        return result;
    }
}
