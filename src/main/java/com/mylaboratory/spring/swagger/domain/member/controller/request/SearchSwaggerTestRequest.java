package com.mylaboratory.spring.swagger.domain.member.controller.request;

import com.mylaboratory.spring.swagger.domain.member.controller.enum_type.MemberType;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// title 속성은 default 값으로 클래스명이 들어감
public class SearchSwaggerTestRequest {

    // Query Parameter 인경우 @Parameter 사용
    @Parameter(description = "이름", example = "11")
    private String name;

    @Parameter(description = "나이", required = true)
    private int age;

    @Parameter(description = "Enum", required = true)
    private MemberType memberType;
}
