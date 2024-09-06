package com.myproject.cart_service.config.auth_service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.myproject.cart_service.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
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
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${auth.url}")
    private String authServiceBaseUrl;

    @Value("${internal.secret}")
    private String internalSecret;

    private final RestTemplate restTemplate;

    private Cache<String, AuthResponse> token2authRespCache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .maximumSize(1_000)
            .build();

    @Override
    public AuthResponse validate(String token) {
        AuthResponse authResponse = token2authRespCache.getIfPresent(token);
        if (authResponse == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Internal", internalSecret);

            HttpEntity<AuthRequest> request = new HttpEntity<>(
                    AuthRequest.builder()
                            .accessToken(token)
                            .build(),
                    httpHeaders
                    );

            ResponseEntity<ApiResponse<AuthResponse>> response = restTemplate.exchange(
                    authServiceBaseUrl + "/internal/validate",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<ApiResponse<AuthResponse>>() {}
            );

            if (response.getBody() != null) {
                authResponse = response.getBody().getResult();
            }
            if (authResponse != null) {
                token2authRespCache.put(token, authResponse);
            }
        }
        if (authResponse == null) {
            return AuthResponse.builder()
                    .authenticated(false)
                    .build();
        }
        return authResponse;
    }

}
