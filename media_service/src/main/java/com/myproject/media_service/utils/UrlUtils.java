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

    public static String getUrl(Media media) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(String.format("/media/view/%1$s/file/%2$s", media.getId(), media.getFileName()))
                .build().toUriString();
    }

}
