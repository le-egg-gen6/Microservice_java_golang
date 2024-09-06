package com.myproject.cart_service.catalog_client;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
public class CatalogClientImpl implements CatalogClient{

	@Value("${service.catalog}")
	private String catalogServiceBaseurl;

	@Value("${internal.secret}")
	private String internalSecret;

	private final RestTemplate restTemplate;

	//private Cache<>


}
