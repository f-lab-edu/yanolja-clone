package com.moondysmell.yanoljaclone.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(this.errorCode.getStatus());
    }
}
