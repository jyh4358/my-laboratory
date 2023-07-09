package com.mylaboratory.spring.validation.controller.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CustomConstraintV1Request {

    @Pattern(regexp = "^\\S+$", message = "이름에 공백 문자가 없어야합니다")
    private String name;
}
