package com.mylaboratory.spring.http_data_binding.dto;

import lombok.Getter;

// getter or setter
// objectmapper가 reflection을 통해 필드에 값들을 넣어준다.
@Getter
public class MemberDto {

    private String name;
    private int age;
    private String cellphone;

    public String getMemberInfo() {
        return name + ", " + age + ", " + cellphone;
    }
}
