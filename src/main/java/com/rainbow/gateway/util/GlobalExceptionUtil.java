//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rainbow.gateway.util;


import java.io.IOException;
import java.io.InvalidClassException;
import java.net.UnknownHostException;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import com.rainbow.gateway.common.ErrorStatusConstants;
import com.rainbow.gateway.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class GlobalExceptionUtil {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionUtil.class);

    public GlobalExceptionUtil() {
    }

    public static Pair<BaseException, Integer> getExceptionMessage(Exception exception, Boolean isDevelopment, String prefix, BiFunction<Exception, Boolean, Pair<BaseException, Integer>> customException) {
        if (customException != null) {
            Pair<BaseException, Integer> customResult = (Pair)customException.apply(exception, isDevelopment);
            return StringUtils.isEmpty(((BaseException)customResult.getLeft()).getErrorCode()) && ((BaseException)customResult.getLeft()).getErrorMessage().length <= 0 ? handleBaseExceptions(exception, prefix, isDevelopment) : customResult;
        } else {
            return handleBaseExceptions(exception, prefix, isDevelopment);
        }
    }

    private static Pair<BaseException, Integer> handleBaseExceptions(Exception exception, String prefix, Boolean isDevelopment) {
        String stackTrace = getStackTrace(exception, isDevelopment);
        BaseException baseException = new BaseException();
        int httpStatusCode = getHttpStatus(exception);
        if (httpStatusCode > -1) {
            Throwable throwable = exception.getCause();
            String pattern = prefix + "\\d{3}.\\d{3}";
            boolean isMatch = Pattern.matches(pattern, throwable.getMessage());
            if (!isMatch) {
                baseException.setErrorCode(prefix + throwable.getMessage());
            } else {
                baseException.setErrorCode(throwable.getMessage());
            }

            baseException.setErrorMessage(new String[]{exception.getMessage()});
            baseException.setStackTrace(stackTrace);
            return Pair.of(baseException, httpStatusCode);
        } else {
            String errorCode;
            String[] errorMessage;
            if (exception instanceof IOException) {
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                errorCode = ErrorStatusConstants.IO_ERROR.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.IO_ERROR.getErrorMessage()};
            } else if (exception instanceof ClassCastException) {
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                errorCode = ErrorStatusConstants.INVALID_CAST.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.INVALID_CAST.getErrorMessage()};
            } else if (exception instanceof IllegalArgumentException) {
                httpStatusCode = HttpStatus.BAD_REQUEST.value();
                errorCode = ErrorStatusConstants.FORMAT_ERROR.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.FORMAT_ERROR.getErrorMessage()};
            } else if (exception instanceof InvalidClassException) {
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                errorCode = ErrorStatusConstants.SERIALIZATION_ERROR.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.SERIALIZATION_ERROR.getErrorMessage()};
            } else if (exception instanceof NullPointerException) {
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                errorCode = ErrorStatusConstants.NULL_POINTER.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.NULL_POINTER.getErrorMessage()};
            } else if (exception instanceof UnknownHostException) {
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                errorCode = ErrorStatusConstants.HTTP_REQUEST_ERROR.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.HTTP_REQUEST_ERROR.getErrorMessage()};
            } else {
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                errorCode = ErrorStatusConstants.NOT_KNOWN.getErrorCode();
                errorMessage = new String[]{ErrorStatusConstants.NOT_KNOWN.getErrorMessage()};
            }

            baseException.setErrorMessage(errorMessage);
            baseException.setErrorCode(prefix + errorCode);
            baseException.setStackTrace(stackTrace);
            return Pair.of(baseException, httpStatusCode);
        }
    }

    public static int getHttpStatus(Exception exception) {
        try {
            Class superClass = exception.getClass().getSuperclass();
            Object httpCodeObject = superClass.getDeclaredField("httpCode").get(exception);
            if (httpCodeObject instanceof Integer) {
                return (Integer)httpCodeObject;
            }
        } catch (Exception var3) {
            log.warn("获取状态码失败:{}", var3);
            return -1;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public static String getErrorCodeWithPrefix(String prefix, Exception exception) {
        Throwable throwable = exception.getCause() == null ? exception : exception.getCause();
        String pattern = prefix + "\\d{3}.\\d{3}";
        boolean isMatch = Pattern.matches(pattern, ((Throwable)throwable).getMessage());
        return !isMatch ? prefix + ((Throwable)throwable).getMessage() : ((Throwable)throwable).getMessage();
    }

    public static String getStackTrace(Exception exception, boolean isDevelopment) {
        Throwable throwable = exception.getCause() == null ? exception : exception.getCause();
        String stackTrace = isDevelopment ? ExceptionUtils.getStackTrace((Throwable)throwable) : null;
        return stackTrace;
    }
}
