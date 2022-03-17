package com.rainbow.gateway.exception;

import com.rainbow.gateway.common.ErrorStatusConstants;

public class AuthenticationException extends RuntimeException {
    public Integer httpCode = 401;

    public AuthenticationException(ErrorStatusConstants status) {
        super(status.getErrorMessage(), new Throwable(status.getErrorCode()));
    }

    public AuthenticationException(String errorCode, String message) {
        super(message, new Throwable(errorCode));
    }

    public AuthenticationException() {
    }
}