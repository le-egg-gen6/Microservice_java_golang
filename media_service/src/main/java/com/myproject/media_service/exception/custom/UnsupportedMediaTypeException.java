package com.myproject.media_service.exception.custom;

/**
 * @author nguyenle
 */
public class UnsupportedMediaTypeException extends RuntimeException {
    public UnsupportedMediaTypeException() {
        super();
    }

    public UnsupportedMediaTypeException(String errorMessage) {
        super(errorMessage);
    }

    public UnsupportedMediaTypeException(Throwable cause) {
        super(cause);
    }

    public UnsupportedMediaTypeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
