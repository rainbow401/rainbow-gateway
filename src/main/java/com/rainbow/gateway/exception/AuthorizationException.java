//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rainbow.gateway.exception;


import com.rainbow.gateway.common.ErrorStatusConstants;

public class AuthorizationException extends RuntimeException {
    public Integer httpCode = 403;

    public AuthorizationException(ErrorStatusConstants status) {
        super(status.getErrorMessage(), new Throwable(status.getErrorCode()));
    }

    public AuthorizationException(String errorCode, String message) {
        super(message, new Throwable(errorCode));
    }

    public AuthorizationException() {
    }
}
