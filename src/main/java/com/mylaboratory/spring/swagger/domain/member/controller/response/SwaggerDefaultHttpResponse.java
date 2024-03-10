package com.mylaboratory.spring.swagger.domain.member.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mylaboratory.spring.validation.common.BaseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@Schema(description = "기본 응답 Dto")
public class SwaggerDefaultHttpResponse<T> {

    @Schema(description = "응답 코드")
    private int returnCode;
    @Schema(description = "응답 메시지")
    private String returnMessage;
    @JsonInclude(Include.NON_NULL)
    private T context;

    public SwaggerDefaultHttpResponse(int returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public SwaggerDefaultHttpResponse(BaseCode baseCode) {
        this(baseCode.code(), baseCode.message());
    }

    public SwaggerDefaultHttpResponse(BaseCode baseCode, T context) {
        this(baseCode.code(), baseCode.message());
        this.context = context;
    }

}
