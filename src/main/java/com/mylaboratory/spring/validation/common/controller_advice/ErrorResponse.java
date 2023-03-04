package com.mylaboratory.spring.validation.common.controller_advice;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int code;

    private final Object message;

    public ErrorResponse(int code, Object message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse error(int code, Object message) {
        return new ErrorResponse(code, message);
    }
}
