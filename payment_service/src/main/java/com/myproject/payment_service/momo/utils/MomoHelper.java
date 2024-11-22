package com.myproject.payment_service.momo.utils;

import lombok.experimental.UtilityClass;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author nguyenle
 * @since 9:50 PM Sun 10/13/2024
 */
@UtilityClass
public class MomoHelper {

    public static String computeHmacSha256(String message, String secretKey) {

        try {

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            byte[] hashBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {

                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {

                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }


}
