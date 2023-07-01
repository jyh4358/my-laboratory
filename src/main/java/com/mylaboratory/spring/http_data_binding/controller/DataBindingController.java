package com.mylaboratory.spring.http_data_binding.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataBindingController {

    @GetMapping("/v0/members/{member_id}")
    public ResponseEntity<String> getMemberDetailV0(
            @PathVariable(name = "member_id") Long memberId
    ) {
        return ResponseEntity.ok("Member id = " + memberId);
    }

    @GetMapping("/v1/members/{member_id}")
    public ResponseEntity<String> getMemberDetailV1(
            @PathVariable("member_id") Long memberId
    ) {
        return ResponseEntity.ok("Member id = " + memberId);
    }

    @GetMapping("/v2/members/{memberId}")
    public ResponseEntity<String> getMemberDetailV2(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok("Member id = " + memberId);
    }
}
