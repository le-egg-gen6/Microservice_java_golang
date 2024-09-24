package com.myproject.notification_service.exception;

/**
 * @author nguyenle
 * @since 10:56 AM Tue 9/24/2024
 */
public class AuthenticationException extends RuntimeException {

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message) {
		super(message);
	}

}
