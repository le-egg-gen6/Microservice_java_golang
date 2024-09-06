package com.myproject.media_service.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 */
@Component
public class SecurityConstant {

	@Value("${internal.secret}")
	private String internalSecret;

	public String getInternalSecret() {
		return internalSecret;
	}
}
