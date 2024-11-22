package com.myproject.cart_service.config.product_service;

import com.myproject.cart_service.payload.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author nguyenle
 * @since 9:01 PM Mon 10/7/2024
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${service.product}")
    private String productServiceBaseUrl;

    @Value("${internal.secret}")
    private String internalSecret;

    private final RestTemplate restTemplate;

    @Override
    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "fallbackValidate"
    )
    @Retry(name = "productService")
    public ValidateCartResponse validate(Map<Long, Integer> products) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Internal", internalSecret);

        HttpEntity<ValidateCartRequest> requestHttpEntity = new HttpEntity<>(
                ValidateCartRequest.builder()
                        .products(products)
                        .build(),
                httpHeaders
        );

        ResponseEntity<ApiResponse<ValidateCartResponse>> response = restTemplate.exchange(
                productServiceBaseUrl + "/internal/validate",
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<ApiResponse<ValidateCartResponse>>() {}
        );

        if (response.getBody() == null) {
            return ValidateCartResponse.builder()
                    .success(false)
                    .invalidProducts(products)
                    .build();
        }
        return response.getBody().getResult();
    }

    @Override
    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "fallbackCalculateCartPrice"
    )
    @Retry(name = "productService")
    public CalculateCartPriceResponse calculateCartPrice(Map<Long, Integer> products) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Internal", internalSecret);

        HttpEntity<CalculateCartPriceRequest> requestHttpEntity = new HttpEntity<>(
                CalculateCartPriceRequest.builder()
                        .products(products)
                        .build(),
                httpHeaders
        );

        ResponseEntity<ApiResponse<CalculateCartPriceResponse>> response = restTemplate.exchange(
                productServiceBaseUrl + "",
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<ApiResponse<CalculateCartPriceResponse>>() {}
        );

        if (response.getBody() == null) {
            return CalculateCartPriceResponse.builder()
                    .price(-1.0)
                    .build();
        }

        return response.getBody().getResult();
    }

    private ValidateCartResponse fallbackValidate(Map<Long, Integer> products) {
        return ValidateCartResponse.builder()
                .success(false)
                .invalidProducts(products)
                .build();
    }

    private CalculateCartPriceResponse fallbackCalculateCartPrice(Map<Long, Integer> products) {
        return CalculateCartPriceResponse.builder()
                .price(-1.0)
                .build();
    }

}
