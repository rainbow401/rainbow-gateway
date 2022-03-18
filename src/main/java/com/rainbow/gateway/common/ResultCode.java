package com.rainbow.gateway.common;


import org.springframework.http.HttpStatus;

/**
 * @author yanzhihao
 * @since 2022/3/18
 */

public enum ResultCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "无操作权限"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "token无效");

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
