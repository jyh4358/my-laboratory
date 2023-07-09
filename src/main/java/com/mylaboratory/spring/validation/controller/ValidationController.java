package com.mylaboratory.spring.validation.controller;

import com.mylaboratory.spring.validation.common.BaseCode;
import com.mylaboratory.spring.validation.common.DefaultHttpResponse;
import com.mylaboratory.spring.validation.controller.request.*;
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

    @PostMapping("/v0/custom-constraint")
    public DefaultHttpResponse<Void> customConstraintV0(
            @Valid @RequestBody CustomConstraintV0Request request
    ) {
        return new DefaultHttpResponse<>(BaseCode.SUCCESS);
    }

    @PostMapping("/v1/custom-constraint")
    public DefaultHttpResponse<Void> customConstraintV1(
            @Valid @RequestBody CustomConstraintV1Request request
    ) {
        return new DefaultHttpResponse<>(BaseCode.SUCCESS);
    }

    @PostMapping("/v2/custom-constraint")
    public DefaultHttpResponse<Void> customConstraintV2(
            @Valid @RequestBody CustomConstraintV2Request request
    ) {
        return new DefaultHttpResponse<>(BaseCode.SUCCESS);
    }
}
