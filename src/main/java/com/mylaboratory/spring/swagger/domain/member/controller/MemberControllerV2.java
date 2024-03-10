package com.mylaboratory.spring.swagger.domain.member.controller;

import com.mylaboratory.spring.swagger.domain.member.controller.request.SaveSwaggerTestRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberControllerV2 {

    @GetMapping("/jd/v2/test")
    public void test(@ParameterObject @Valid @ModelAttribute SaveSwaggerTestRequest request) {


    }
}
