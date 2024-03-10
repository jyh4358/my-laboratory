package com.mylaboratory.spring.swagger.domain.member.controller.request;

import com.mylaboratory.spring.swagger.domain.member.controller.enum_type.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// title 속성은 default 값으로 클래스명이 들어감
@Schema(description = "멤버 등록 Dto")
public class SaveSwaggerTestRequest {

    @Schema(description = "이름", example = "11")
    private String name;

    @Schema(description = "나이", requiredMode = Schema.RequiredMode.REQUIRED)
    private int age;

    @Schema(description = "enum 1", requiredMode = Schema.RequiredMode.REQUIRED)
    private MemberType memberType;
}
