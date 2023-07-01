package com.mylaboratory.spring.http_url_mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v2/order")
public class V2MappingController {

    // 요청 URL = http://localhost:8080/order
    @RequestMapping
    public String defaultUrl() {
        return "value = order";
    }

    // 요청 URL = "http://localhost:8080/order/member"
    @RequestMapping(value = "/member")
    public String valueUrl() {
        return "value = order/member";
    }
}
