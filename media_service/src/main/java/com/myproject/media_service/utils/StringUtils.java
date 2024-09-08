package com.myproject.media_service.utils;

import lombok.experimental.UtilityClass;

/**
 * @author nguyenle
 */
@UtilityClass
public class StringUtils {

    public static boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }

}
