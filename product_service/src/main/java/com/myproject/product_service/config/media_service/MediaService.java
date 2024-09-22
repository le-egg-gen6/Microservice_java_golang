package com.myproject.product_service.config.media_service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author nguyenle
 * @since 7:40 AM Fri 9/13/2024
 */
public interface MediaService {

    MediaResponse saveMedia(MultipartFile file);

    MediaResponse getMediaInformation(Long mediaId);

}
