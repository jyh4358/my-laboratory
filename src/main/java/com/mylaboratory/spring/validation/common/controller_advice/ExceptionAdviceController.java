package com.mylaboratory.spring.validation.common.controller_advice;

import com.mylaboratory.spring.validation.common.BaseCode;
import com.mylaboratory.spring.validation.common.DefaultHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackages = "com.mylaboratory.spring.validation")
public class ExceptionAdviceController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultHttpResponse<String> methodArgumentNotValid(MethodArgumentNotValidException ex) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();

        for (int i = 0; i < fieldErrors.size(); i++) {
            if (StringUtils.hasText(fieldErrors.get(i).getDefaultMessage())) {
                errorMessage.append(fieldErrors.get(i).getField());
                errorMessage.append(" : ");
                errorMessage.append(fieldErrors.get(i).getDefaultMessage());
                if (i != fieldErrors.size() - 1) {
                    errorMessage.append("\n");
                }
            }
        }
        return new DefaultHttpResponse<>(BaseCode.ERR_BASIC_ARG_IS_WRONG, errorMessage.toString());
    }
}
