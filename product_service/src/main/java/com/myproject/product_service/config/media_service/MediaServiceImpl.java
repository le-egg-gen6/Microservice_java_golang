package com.myproject.product_service.config.media_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

}
