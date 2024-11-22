package com.myproject.payment_service.zalopay.service.impl;

import com.myproject.payment_service.zalopay.config.ZaloPayConfig;
import com.myproject.payment_service.zalopay.dto.ZaloPayOrderRequestDTO;
import com.myproject.payment_service.zalopay.dto.ZaloPayStatusRequestDTO;
import com.myproject.payment_service.zalopay.service.ZaloPayCoreService;
import com.myproject.payment_service.zalopay.utils.HMACUtils;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author nguyenle
 * @since 4:47 PM Sun 10/13/2024
 */
@Service
@RequiredArgsConstructor
public class ZaloPayCoreServiceImpl implements ZaloPayCoreService {

    private final ZaloPayConfig zaloPayConfig;

    private String getCurrentTimeString(String format) {

        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    @Override
    public Map<String, String> createOrder(ZaloPayOrderRequestDTO orderRequestDTO) {
        Map<String, Object> order = new HashMap<>(){{
            put("appid", zaloPayConfig.getAppId());
            put("apptransid", getCurrentTimeString("yyMMdd") +"_"+ new Date().getTime()); // translation missing: vi.docs.shared.sample_code.comments.app_trans_id
            put("apptime", System.currentTimeMillis()); // miliseconds
            put("appuser", zaloPayOrderRequestDTO.getAppUser());
            put("amount", zaloPayOrderRequestDTO.getAmount());
            put("description", "Lazada - Payment for the order #" + zaloPayOrderRequestDTO.getOrderId());
            put("bankcode", "");
            put("item", "[]");
            put("embeddata", "{}");
            put("callback_url", "http://localhost:8080/api/v1/callback");
        }};

        String data = order.get("appid") +"|"+ order.get("apptransid") +"|"+ order.get("appuser") +"|"+ order.get("amount")
                +"|"+ order.get("apptime") +"|"+ order.get("embeddata") +"|"+ order.get("item");
        order.put("mac", HMACUtils.HMacHexStringEncode(HMACUtils.HMACSHA256, zaloPayConfig.getKey1(), data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(ZalopayConstant.ORDER_CREATE_ENDPOINT);

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {

            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {

            resultJsonStr.append(line);
        }

        JSONObject jsonResult = new JSONObject(resultJsonStr.toString());
        Map<String, Object> finalResult = new HashMap<>();
        for (Iterator it = jsonResult.keys(); it.hasNext(); ) {

            String key = (String) it.next();
            finalResult.put(key, jsonResult.get(key));
        }

        return finalResult;
    }

    @Override
    public Map<String, Object> statusOrder(ZaloPayStatusRequestDTO statusRequestDTO) {
        //        String appTranId = "210608_2553_1623145380738";  // Input your app_trans_id
        String data = ZalopayConstant.APP_ID +"|"+ zaloPayStatusRequestDTO.getAppTransId()  +"|"+ ZalopayConstant.KEY1; // appid|app_trans_id|key1
        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, ZalopayConstant.KEY1, data);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", ZalopayConstant.APP_ID));
        params.add(new BasicNameValuePair("apptransid", zaloPayStatusRequestDTO.getAppTransId()));
        params.add(new BasicNameValuePair("mac", mac));

        URIBuilder uri = new URIBuilder(ZalopayConstant.ORDER_STATUS_ENDPOINT);
        uri.addParameters(params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri.build());
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {

            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        Map<String, Object> finalResult = new HashMap<>();
        finalResult.put("returncode", result.get("returncode"));
        finalResult.put("returnmessage", result.get("returnmessage"));
        finalResult.put("isprocessing", result.get("isprocessing"));
        finalResult.put("amount", result.get("amount"));
        finalResult.put("discountamount", result.get("discountamount"));
        finalResult.put("zptransid", result.get("zptransid"));
        return finalResult;
    }

    @Override
    public Object doCallback(JSONObject result, String jsonStr) {
        Mac HmacSHA256 = Mac.getInstance("HmacSHA256");
        HmacSHA256.init(new SecretKeySpec(ZalopayConstant.KEY2.getBytes(), "HmacSHA256"));

        try {
            JSONObject cbdata = new JSONObject(jsonStr);
            String dataStr = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");

            byte[] hashBytes = HmacSHA256.doFinal(dataStr.getBytes());
            String mac = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

            // check if the callback is valid (from ZaloPay server)
            if (!reqMac.equals(mac)) {
                // callback is invalid
                result.put("returncode", -1);
                result.put("returnmessage", "mac not equal");
            } else {
                // payment success
                // merchant update status for order's status
                JSONObject data = new JSONObject(dataStr);
                logger.info("update order's status = success where app_trans_id = " + data.getString("app_trans_id"));

                result.put("return_code", 1);
                result.put("return_message", "success");
            }
        } catch (Exception ex) {
            result.put("return_code", 0); // callback again (up to 3 times)
            result.put("return_message", ex.getMessage());
        }

        return result;
    }

}
