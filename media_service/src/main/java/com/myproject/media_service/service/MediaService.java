package com.myproject.media_service.service;

import com.myproject.media_service.dto.MediaDto;
import com.myproject.media_service.payload.request.MediaUploadRequest;
import com.myproject.media_service.payload.response.MediaResponse;
import com.myproject.media_service.payload.response.NoMediaResponse;

/**
 * @author nguyenle
 */
public interface MediaService {

    NoMediaResponse saveMedia(MediaUploadRequest request);

    MediaResponse getMediaById(Long id);

    MediaResponse getMediaByName(String fileName);

    MediaDto getFileResource(Long id, String fileName);
}
