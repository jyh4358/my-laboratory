package com.mylaboratory.spring.validation.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class DefaultHttpResponse<T> {

    private int returnCode;
    private String returnMessage;
    @JsonInclude(Include.NON_NULL)
    private T context;

    public DefaultHttpResponse(int returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public DefaultHttpResponse(BaseCode baseCode) {
        this(baseCode.code(), baseCode.message());
    }

    public DefaultHttpResponse(BaseCode baseCode, T context) {
        this(baseCode.code(), baseCode.message());
        this.context = context;
    }

}
