package com.myproject.notification_service.exception;

import com.myproject.notification_service.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author nguyenle
 * @since 10:55 AM Tue 9/24/2024
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<ApiResponse<?>> handlingAuthenticationException(AuthenticationException authenticationException) {
		log.error("Authentication exception: ", authenticationException);
		ApiResponse<?> apiResponse = ApiResponse.errorResponse(HttpStatus.UNAUTHORIZED, "Please login!");
		return ResponseEntity.ok(apiResponse);
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException runtimeException) {
		log.error("Exception: ", runtimeException);
		ApiResponse<?> apiResponse = ApiResponse.errorResponse(HttpStatus.BAD_REQUEST, "An error occurred, please try again!");
		return ResponseEntity.ok(apiResponse);
	}

}
