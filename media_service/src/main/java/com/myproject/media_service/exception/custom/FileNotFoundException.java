package com.myproject.media_service.exception.custom;

/**
 * @author nguyenle
 */
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }

    public FileNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
