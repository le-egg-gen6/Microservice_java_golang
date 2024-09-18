package com.myproject.product_service.config.media_service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.product_service.payload.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 * @since 7:40 AM Fri 9/13/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Value("${service.media}")
    private String mediaServiceBaseUrl;

    @Value("${internal.secret}")
    private String internalSecret;

    private final RestTemplate restTemplate;

    private Cache<Long, MediaResponse> id2MediaResponse = Caffeine.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();

    @Override
    @CircuitBreaker(
            name = "mediaService",
            fallbackMethod = "fallbackSaveMedia"
    )
    @Retry(name = "mediaService")
    public MediaResponse saveMedia(MediaUploadRequest request) {
        log.info("Saving media from product service");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Internal", internalSecret);

        HttpEntity<MediaUploadRequest> request2Client = new HttpEntity<>(
                request,
                httpHeaders
        );

        ResponseEntity<ApiResponse<MediaResponse>> response = restTemplate.exchange(
            mediaServiceBaseUrl + "/internal/media/create",
            HttpMethod.POST,
            request2Client,
            new ParameterizedTypeReference<ApiResponse<MediaResponse>>() {}
        );
        MediaResponse mediaResponse = MediaResponse.builder()
            .id(-1L)
            .fileName("")
            .mediaType("")
            .build();
        if (response.getBody() != null) {
            mediaResponse = response.getBody().getResult();
        }
        if (mediaResponse != null && mediaResponse.getId() != -1L) {
            id2MediaResponse.put(mediaResponse.getId(), mediaResponse);
        }
        return mediaResponse;
    }

    private MediaResponse fallbackSaveMedia(MediaUploadRequest request, Throwable throwable) {
        log.error("Media service take so much time to response, please check!");
        return MediaResponse.builder()
                .id(-1L)
                .mediaType("")
                .fileName("")
                .build();
    }

    @Override
    @CircuitBreaker(
            name = "mediaService",
            fallbackMethod = "fallbackGetMediaInformation"
    )
    @Retry(name = "mediaService")
    public MediaResponse getMediaInformation(Long mediaId) {
        if (id2MediaResponse.getIfPresent(mediaId) != null) {
            return id2MediaResponse.getIfPresent(mediaId);
        }
        log.info("Get media info from media service");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Internal", internalSecret);


        HttpEntity<MediaUploadRequest> request2Client = new HttpEntity<>(
                httpHeaders
        );

        ResponseEntity<ApiResponse<MediaResponse>> response = restTemplate.exchange(
                mediaServiceBaseUrl + "/internal/media/info/" + mediaId,
                HttpMethod.GET,
                request2Client,
                new ParameterizedTypeReference<ApiResponse<MediaResponse>>() {}
        );
        MediaResponse mediaResponse = MediaResponse.builder()
                .id(-1L)
                .fileName("")
                .mediaType("")
                .build();
        if (response.getBody() != null) {
            mediaResponse = response.getBody().getResult();
        }
        return mediaResponse;
    }

    private MediaResponse fallbackGetMediaInformation(Long mediaId, Throwable throwable) {
        return MediaResponse.builder()
                .id(-1L)
                .mediaType("")
                .fileName("")
                .url("")
                .build();
    }
}
