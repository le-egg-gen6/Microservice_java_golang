package com.myproject.media_service.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.media_service.dto.MediaDto;
import com.myproject.media_service.entity.Media;
import com.myproject.media_service.exception.custom.FileNotFoundException;
import com.myproject.media_service.exception.custom.MultipartFileContentException;
import com.myproject.media_service.exception.custom.UnsupportedMediaTypeException;
import com.myproject.media_service.payload.request.MediaUploadRequest;
import com.myproject.media_service.payload.response.MediaResponse;
import com.myproject.media_service.payload.response.NoMediaResponse;
import com.myproject.media_service.repository.MediaRepository;
import com.myproject.media_service.service.MediaService;
import com.myproject.media_service.utils.MediaUtils;
import com.myproject.media_service.utils.StringUtils;
import com.myproject.media_service.utils.UrlUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    private Cache<String, Media> fileName2Media = Caffeine.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();

    private Cache<Long, Media> id2Media = Caffeine.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();


    @Override
    public NoMediaResponse saveMedia(MediaUploadRequest request) {
        if (!(MediaUtils.isFileTypeValid(request.getMultipartFile()))) {
            log.error("File type not accepted");
            throw new UnsupportedMediaTypeException();
        }
        Media media = new Media();
        media.setCaption(request.getCaption());
        media.setMediaType(request.getMultipartFile().getContentType());

        try {
            media.setData(request.getMultipartFile().getBytes());
        } catch (IOException e) {
            log.error("Multipartfile content IO error");
            throw new MultipartFileContentException(e);
        }

        if (!StringUtils.hasText(request.getFileName())) {
            media.setFileName(request.getMultipartFile().getOriginalFilename());
        } else {
            media.setFileName(request.getFileName());
        }

        media = mediaRepository.saveAndFlush(media);
        log.info("File " + MediaUtils.getMediaLogStr(media) + " saved");
        fileName2Media.put(media.getFileName(), media);
        id2Media.put(media.getId(), media);
        return NoMediaResponse.builder()
                .id(media.getId())
                .mediaType(media.getMediaType())
                .caption(media.getCaption())
                .fileName(media.getFileName())
                .build();
    }

    @Override
    public MediaResponse getMediaById(Long id) {
        Media media = id2Media.getIfPresent(id);
        if (media == null) {
            media = mediaRepository.findById(id).orElseThrow(
                    () -> {
                        log.error("File " + id + " not existed");
                        throw new FileNotFoundException();
                    }
            );
            id2Media.put(media.getId(), media);
            fileName2Media.put(media.getFileName(), media);
        }
        String url = UrlUtils.getUrl(media);
        return MediaResponse.builder()
                .id(media.getId())
                .mediaType(media.getMediaType())
                .caption(media.getCaption())
                .fileName(media.getFileName())
                .url(url)
                .build();

    }

    @Override
    public MediaResponse getMediaByName(String fileName) {
        Media media = fileName2Media.getIfPresent(fileName);
        if (media == null) {
            media = mediaRepository.findByFileName(fileName).orElseThrow(
                () -> {
                    log.error("File " + fileName + " not existed");
                    throw new FileNotFoundException();
                }
            );
            id2Media.put(media.getId(), media);
            fileName2Media.put(media.getFileName(), media);
        }
        String url = UrlUtils.getUrl(media);
        return MediaResponse.builder()
            .id(media.getId())
            .mediaType(media.getMediaType())
            .caption(media.getCaption())
            .fileName(media.getFileName())
            .url(url)
            .build();
    }

    @Override
    public MediaDto getFileResource(Long id, String fileName) {
        Media media = mediaRepository.findById(id).orElse(null);
        if (media == null || fileName.equalsIgnoreCase(media.getFileName())) {
            return MediaDto.builder().build();
        }
        MediaType mediaType = MediaType.valueOf(media.getMediaType());
        InputStream inputStream = new ByteArrayInputStream(media.getData());
        return MediaDto.builder()
            .mediaType(mediaType)
            .content(inputStream)
            .build();
    }
}
