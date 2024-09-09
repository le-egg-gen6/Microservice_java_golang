package com.myproject.media_service.utils;

import com.myproject.media_service.entity.Media;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author nguyenle
 */
@UtilityClass
public class MediaUtils {

    public static boolean isFileTypeValid(@NotNull MultipartFile file) {
        MediaType mediaType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
        return MediaType.IMAGE_PNG.equals(mediaType)
                || MediaType.IMAGE_JPEG.equals(mediaType)
                || MediaType.IMAGE_GIF.equals(mediaType);
    }

    public static String getMediaLogStr(Media media) {
        return media.getId() + "_" + media.getFileName() + "_" + media.getMediaType();
    }

}
