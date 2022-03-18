package com.rainbow.gateway.common;

import lombok.Data;

/**
 * @author yanzhihao
 * @since 2022/3/18
 */
@Data
public class Result {

    private Integer code;

    private String message;

    private boolean success;

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
