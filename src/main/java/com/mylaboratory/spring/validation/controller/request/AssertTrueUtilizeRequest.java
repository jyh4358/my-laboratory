package com.mylaboratory.spring.validation.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

@Getter
@Setter
public class AssertTrueUtilizeRequest {

    @Email
    private String email;

    private String cellPhone;

    @AssertTrue(message = "이메일과 전화번호를 잘못입력하였거나 둘 다 입력하지 않았습니다.")
    @JsonIgnore
    public Boolean getEmailAndCellPhoneValidation() {
        String cellPhonePattern = "^((0([1-9]\\d))|02)\\d{3,4}\\d{4}$";
        cellPhone = Optional.ofNullable(cellPhone).orElse("");
        email = Optional.ofNullable(email).orElse("");

        if (cellPhone.isBlank() && email.isBlank()) {
            return false;
        }

        if (cellPhone.isBlank() && StringUtils.hasText(email)) {
            return true;
        }

        return Pattern.matches(cellPhonePattern, cellPhone);
    }

}
