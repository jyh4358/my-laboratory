package com.mylaboratory.spring.validation.common;

public enum BaseCode {

    SUCCESS(200, "Success"),
    ERR_BASIC_ARG_IS_WRONG(400, "Argument is wrong"),
    ;

    private final int code;
    private final String message;

    BaseCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
