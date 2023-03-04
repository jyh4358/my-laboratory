package com.mylaboratory.spring.validation.common.controller;

import com.mylaboratory.spring.validation.common.BaseCode;
import com.mylaboratory.spring.validation.common.DefaultHttpResponse;
import com.mylaboratory.spring.validation.common.controller.request.AssertTrueUtilizeRequest;
import com.mylaboratory.spring.validation.common.controller.request.CustomConstraintRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @PostMapping("/custom-constraint")
    public DefaultHttpResponse<Void> customConstraint(
            @Valid @RequestBody CustomConstraintRequest request
    ) {
        return new DefaultHttpResponse<>(BaseCode.SUCCESS);
    }

    @PostMapping("/assert-true-utilize")
    public DefaultHttpResponse<Void> assertTrueUtilize(
            @Valid @RequestBody AssertTrueUtilizeRequest request
            ) {
        return new DefaultHttpResponse<>(BaseCode.SUCCESS);
    }
}
