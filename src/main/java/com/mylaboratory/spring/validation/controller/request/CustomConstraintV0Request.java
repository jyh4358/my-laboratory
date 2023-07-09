package com.mylaboratory.spring.validation.controller.request;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class CustomConstraintV0Request {

    private String name;
    private String nickName;

    @AssertTrue(message = "이름과 닉네임 중 하나는 입력해주세요.")
    public boolean getNameAndNickNameValidation() {
        return StringUtils.hasText(name) || StringUtils.hasText(nickName) ? true : false;
    }
}
