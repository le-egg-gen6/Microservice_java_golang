package com.myproject.product_service.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.product_service.payload.shared.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author nguyenle
 */
@Component
@RequiredArgsConstructor
public class InternalServiceFilter extends OncePerRequestFilter {

	private final SecurityConstant securityConstant;

	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		String path = request.getRequestURI();
		if (isInternalEndpoint(path)) {
			String internalHeader = extractInternalHeader(request);
			if (internalHeader.equals(securityConstant.getInternalSecret())) {
				filterChain.doFilter(request, response);
				return;
			}
			handleUnauthorizedAccess(response);
		} else {
			filterChain.doFilter(request, response);
		}
	}

	public boolean isInternalEndpoint(String path) {
		return path.startsWith("/internal");
	}

	private String extractInternalHeader(HttpServletRequest request) {
		String internalHeader = request.getHeader("Internal");
		if (internalHeader == null || internalHeader.isEmpty()) {
			return "";
		}
		return internalHeader;
	}

	private void handleUnauthorizedAccess(HttpServletResponse response) throws IOException {
		ApiResponse<Object> apiResponse = new ApiResponse<>(401, "Unauthorized access", null);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		objectMapper.writeValue(response.getOutputStream(), apiResponse);
	}
}
