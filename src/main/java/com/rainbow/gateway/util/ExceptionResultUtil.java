package com.rainbow.gateway.util;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.gateway.common.CommonConstants;
import com.rainbow.gateway.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常信息处理类
 *
 * @author puritykid
 * @date 2021-07-05 11:53
 */
public class ExceptionResultUtil {

    private ExceptionResultUtil() {

    }


    public static Map<String, Object> getBaseException(Exception e) {
        Map<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("errorCode", CommonConstants.ERROR_PREFIX + e.getCause().getMessage());
        resultMap.put("errorMessage", new String[]{e.getMessage()});
        resultMap.put("stackTrace", GlobalExceptionUtil.getStackTrace(e, true));
        return resultMap;
    }

    public static String initSecurityException(HttpStatus httpStatus) {
        if (httpStatus == HttpStatus.UNAUTHORIZED) {
            return JSONObject.toJSONString(getBaseException(new InvalidTokenException()));
        } else if (httpStatus == HttpStatus.FORBIDDEN) {
            return JSONObject.toJSONString(getBaseException(new InvalidTokenException()));
        } else {
            return null;
        }
    }
}
