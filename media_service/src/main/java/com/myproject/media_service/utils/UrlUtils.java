package com.myproject.media_service.utils;

import com.myproject.media_service.entity.Media;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author nguyenle
 */
@UtilityClass
public class UrlUtils {

    @Value("${service.media}")
    private String baseUrl;

    public static Long getId(String hashId) {
        int idx = hashId.indexOf("_");
        if (idx != -1) {
            String strId = hashId.substring(0, idx);
            try {
                return (long) Integer.parseInt(strId);
            } catch (NumberFormatException ex) {
                return -1L;
            }
        }
        return -1L;
    }

    public static String getFileName(String hashId) {
        int idx = hashId.indexOf("_");
        if (idx != -1) {
            return hashId.substring(idx + 1, hashId.length() - 1);
        }
        return "";
    }

    private static String getHashId(Media media) {
        return media.getId() + "_" + media.getFileName();
    }

    public static String getUrl(Media media) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(String.format("/media/view/%1$s", getHashId(media)))
                .build().toUriString();
    }

}
