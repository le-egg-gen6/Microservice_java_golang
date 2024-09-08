package com.myproject.media_service.exception;

import com.myproject.media_service.exception.custom.FileNotFoundException;
import com.myproject.media_service.exception.custom.MultipartFileContentException;
import com.myproject.media_service.exception.custom.UnsupportedMediaTypeException;
import com.myproject.media_service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author nguyenle
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<ApiResponse<?>> handleUnsupportedMediaTypeException() {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Unsupported media type")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFoundException() {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("File not found")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartFileContentException.class)
    public ResponseEntity<ApiResponse<?>> handleMultipartFileContentException() {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An error occurred when save file")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An error occurred, please try again")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
