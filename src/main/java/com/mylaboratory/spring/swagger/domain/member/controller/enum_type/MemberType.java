package com.mylaboratory.spring.swagger.domain.member.controller.enum_type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    MEMBER("회원"),
    ADMIN("관리자"),
    ;

    private String desc;

}
