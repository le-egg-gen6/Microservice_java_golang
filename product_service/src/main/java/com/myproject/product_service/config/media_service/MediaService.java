package com.myproject.product_service.config.media_service;

/**
 * @author nguyenle
 * @since 7:40 AM Fri 9/13/2024
 */
public interface MediaService {

    NoMediaResponse saveMedia(MediaUploadRequest request);

    MediaResponse getMediaInformation(Long mediaId);

}
