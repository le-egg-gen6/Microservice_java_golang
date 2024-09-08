package com.myproject.media_service.exception.custom;

/**
 * @author nguyenle
 */
public class MultipartFileContentException extends RuntimeException {

    public MultipartFileContentException() {
        super();
    }

    public MultipartFileContentException(String errorMessage) {
        super(errorMessage);
    }

    public MultipartFileContentException(Throwable cause) {
        super(cause);
    }

    public MultipartFileContentException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}