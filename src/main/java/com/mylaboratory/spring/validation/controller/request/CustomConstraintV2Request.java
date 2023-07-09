package com.mylaboratory.spring.validation.controller.request;

import com.mylaboratory.spring.http_data_binding.dto.MemberDto;
import com.mylaboratory.spring.validation.common.custom_validation.NoWhitespace;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomConstraintV2Request {

    @NoWhitespace
    private String name;

    @NoWhitespace
    private List<String> nameList = new ArrayList<>();

}
