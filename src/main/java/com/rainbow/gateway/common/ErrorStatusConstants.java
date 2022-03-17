//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rainbow.gateway.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum ErrorStatusConstants {
    NOT_KNOWN("100.000", "服务器发生未知错误，请重试"),
    IO_ERROR("100.001", "IO操作异常"),
    STACK_OVERFLOW("100.002", "栈溢出"),
    INVALID_CAST("100.003", "指定的转换无效"),
    FORMAT_ERROR("100.004", "输入的字符串格式不正确"),
    SERIALIZATION_ERROR("100.005", "序列化错误"),
    KEY_NOT_FOUND("100.006", "给定关键字不在字典中"),
    NULL_POINTER("100.007", "空指针异常"),
    ARGUMENT_NULL("100.008", "参数值不能为空"),
    NOT_IMPLEMENT("100.009", "存在未实现的方法"),
    INVALID_TOKEN("101.001", "token无效"),
    INVALID_SCOPE("101.002", "scope无效"),
    PERMISSION_DENIED("102.001", "权限受限"),
    NOT_FOUND_DATA("103.001", "数据不存在"),
    NOT_FOUND_RESOURCE("103.002", "资源不存在"),
    NULL_PARAMETER("104.001", "参数不能为空"),
    LENGTH_PARAMETER("104.002", "参数长度错误"),
    INVALID_PARAMETER("104.003", "参数无效"),
    INVALID_RANGE("104.004", "参数界限值错误"),
    FOUND_DATA("104.005", "数据已存在"),
    FOUND_RESOURCE("104.006", "资源已存在"),
    HTTP_REQUEST_ERROR("105.001", "找不到主机"),
    HTTP_RESPONSE_ERROR("105.002", "Http调用异常");

    private String errorCode;
    private String errorMessage;
    private static Map<String, ErrorStatusConstants> map = new HashMap();

    public static ErrorStatusConstants valueEnumOf(String errorCode, String prefix) {
        String pattern = prefix + "\\d{3}.\\d{3}";
        boolean isMatch = Pattern.matches(pattern, errorCode);
        if (isMatch) {
            errorCode = errorCode.replace(prefix, "");
        }

        return (ErrorStatusConstants)map.get(errorCode);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    private ErrorStatusConstants(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    static {
        ErrorStatusConstants[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            ErrorStatusConstants errorEnum = var0[var2];
            map.put(errorEnum.errorCode, errorEnum);
        }

    }
}
