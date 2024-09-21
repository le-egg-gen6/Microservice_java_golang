package com.myproject.user_service.config.media_service;

/**
 * @author nguyenle
 * @since 7:40 AM Fri 9/13/2024
 */
public interface MediaService {

    MediaResponse saveMedia(MediaUploadRequest request);

    MediaResponse getMediaInformation(Long mediaId);

}
