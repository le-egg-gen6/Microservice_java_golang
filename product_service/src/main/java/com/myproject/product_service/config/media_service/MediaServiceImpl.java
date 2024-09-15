package com.myproject.product_service.config.media_service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    private Cache<Long, String> id2MediaUrlCache = Caffeine.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .maximumSize(1_000)
            .build();

    @Override
    @CircuitBreaker(
            name = "mediaService",
            fallbackMethod = "fallbackSaveMedia"
    )
    @Retry(name = "mediaService")
    public NoMediaResponse saveMedia(MediaUploadRequest request) {
        log.info("Saving media from product service");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Internal", internalSecret);

        HttpEntity<MediaUploadRequest> request = new HttpEntity<>(

                httpHeaders
        );
    }

    private NoMediaResponse fallbackSaveMedia(MediaUploadRequest request, Throwable throwable) {
        log.error("Media service take so much time to response, please check!");
        return NoMediaResponse.builder()
                .id(-1L)
                .mediaType("")
                .caption("")
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
        return null;
    }

    private MediaResponse fallbackGetMediaInformation(Long mediaId, Throwable throwable) {
        return MediaResponse.builder()
                .id(-1L)
                .mediaType("")
                .caption("")
                .fileName("")
                .url("")
                .build();
    }
}
