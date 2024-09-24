package com.myproject.product_service.exception;

import com.myproject.product_service.payload.shared.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author nguyenle
 * @since 5:34 PM Sun 9/22/2024
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

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlingItemNotFoundException(ItemNotFoundException itemNotFoundException) {
        log.error("Item not found exception: ", itemNotFoundException);
        ApiResponse<?> apiResponse = ApiResponse.errorResponse(HttpStatus.NOT_FOUND, "Item not found!");
        return ResponseEntity.ok(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handlingValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("Validation constraint is violated: ", methodArgumentNotValidException);
        StringBuilder message = new StringBuilder();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(
                err -> message.append(err.getDefaultMessage())
        );
        ApiResponse<?> apiResponse = ApiResponse.errorResponse(HttpStatus.BAD_REQUEST, message.toString());
        return ResponseEntity.ok(apiResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException runtimeException) {
        log.error("Exception: ", runtimeException);
        ApiResponse<?> apiResponse = ApiResponse.errorResponse(HttpStatus.BAD_REQUEST, "An error occurred, please try again!");
        return ResponseEntity.ok(apiResponse);
    }

}
