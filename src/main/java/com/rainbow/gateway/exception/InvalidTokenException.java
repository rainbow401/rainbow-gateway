//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rainbow.gateway.exception;


import com.rainbow.gateway.common.ErrorStatusConstants;
import com.rainbow.gateway.exception.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super(ErrorStatusConstants.INVALID_TOKEN);
    }

    public InvalidTokenException(String message) {
        super(ErrorStatusConstants.INVALID_SCOPE.getErrorCode(), message);
    }
}
