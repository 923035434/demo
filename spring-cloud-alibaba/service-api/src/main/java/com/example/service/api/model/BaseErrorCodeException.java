package com.example.service.api.model;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseErrorCodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    @Getter
    @Setter
    private String errorCode;

    protected BaseErrorCodeException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
