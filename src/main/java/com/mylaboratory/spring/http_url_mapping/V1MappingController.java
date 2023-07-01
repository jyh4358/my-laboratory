package com.mylaboratory.spring.http_url_mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class V1MappingController {

    // 요청 URL = http://localhost:8080
    @RequestMapping(value = "/")
    public String defaultUrl() {
        return "value = /";
    }

    // 요청 URL = "http://localhost:8080/member"
    @RequestMapping(value = "/member-auth")
    public String valueUrl() {
        return "value = /member";
    }
}
