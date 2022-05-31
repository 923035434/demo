package com.example.service.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * API调用结果对象
 */
@NoArgsConstructor
@Data
public class CommonResult<T> {
    // 错误码
    private String errorCode;

    // 错误消息
    private String errorMessage;

    // 返回结果
    private T data;

    public CommonResult(T data) {
        this.errorCode = ErrorCode.SUCCESS;
        this.data = data;
    }

    public CommonResult(BaseErrorCodeException ex) {
        this.errorCode = ex.getErrorCode();
        this.errorMessage = ex.getMessage();
    }

    public CommonResult(T data, String errorCode, String errorMessage) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * API调用是否成功
     * 
     * @return 是否成功
     */
    public boolean isSuccess() {
        return ErrorCode.SUCCESS.equals(errorCode);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(data);
    }

    public static <T> CommonResult<T> error(BaseErrorCodeException ex) {
        return new CommonResult<>(ex);
    }

    public static <T> CommonResult<T> error(String errorCode, String errorMessage) {
        return new CommonResult<>(null, errorCode, errorMessage);
    }
}
