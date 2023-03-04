package com.mylaboratory.spring.validation.common.custom_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotContainBlankValidator.class)
public @interface NotContainBlank {

    String message() default "공백이 포함되어 있습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
