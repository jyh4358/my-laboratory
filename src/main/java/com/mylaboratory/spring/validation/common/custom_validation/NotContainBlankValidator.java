package com.mylaboratory.spring.validation.common.custom_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class NotContainBlankValidator implements ConstraintValidator<NotContainBlank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.containsWhitespace(value);
    }
}
