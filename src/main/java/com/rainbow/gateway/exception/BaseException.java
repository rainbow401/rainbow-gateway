//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rainbow.gateway.exception;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class BaseException implements Serializable {
    private String errorCode;
    private String[] errorMessage;
    private String stackTrace;

    public BaseException() {
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String[] getErrorMessage() {
        return this.errorMessage;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String[] errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseException)) {
            return false;
        } else {
            BaseException other = (BaseException)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label39: {
                    Object this$errorCode = this.getErrorCode();
                    Object other$errorCode = other.getErrorCode();
                    if (this$errorCode == null) {
                        if (other$errorCode == null) {
                            break label39;
                        }
                    } else if (this$errorCode.equals(other$errorCode)) {
                        break label39;
                    }

                    return false;
                }

                if (!Arrays.deepEquals(this.getErrorMessage(), other.getErrorMessage())) {
                    return false;
                } else {
                    Object this$stackTrace = this.getStackTrace();
                    Object other$stackTrace = other.getStackTrace();
                    if (this$stackTrace == null) {
                        if (other$stackTrace != null) {
                            return false;
                        }
                    } else if (!this$stackTrace.equals(other$stackTrace)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseException;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(errorCode, stackTrace);
        result = 31 * result + Arrays.hashCode(errorMessage);
        return result;
    }

    @Override
    public String toString() {
        return "BaseException(errorCode=" + this.getErrorCode() + ", errorMessage=" + Arrays.deepToString(this.getErrorMessage()) + ", stackTrace=" + this.getStackTrace() + ")";
    }
}
