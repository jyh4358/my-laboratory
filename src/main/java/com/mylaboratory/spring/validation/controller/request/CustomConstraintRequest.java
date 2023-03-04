package com.mylaboratory.spring.validation.controller.request;

import com.mylaboratory.spring.validation.common.custom_validation.NotContainBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomConstraintRequest {

    private List<@NotContainBlank String> nameList = new ArrayList<>();

    @NotContainBlank
    private String name;
}
