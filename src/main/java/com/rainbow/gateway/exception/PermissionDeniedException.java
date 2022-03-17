//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rainbow.gateway.exception;


import com.rainbow.gateway.common.ErrorStatusConstants;

public class PermissionDeniedException extends AuthorizationException {
    public PermissionDeniedException() {
        super(ErrorStatusConstants.PERMISSION_DENIED);
    }

    public PermissionDeniedException(String message) {
        super(ErrorStatusConstants.PERMISSION_DENIED.getErrorCode(), message);
    }
}
