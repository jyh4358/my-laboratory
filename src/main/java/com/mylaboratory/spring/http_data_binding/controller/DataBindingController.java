package com.mylaboratory.spring.http_data_binding.controller;

import com.mylaboratory.spring.http_data_binding.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 첫 번째 예시
    // 속성 명, 속성 값 모두 명시
    @GetMapping("/v0/request-param/members")
    public ResponseEntity<String> getMemberDetailV0RequestParam(
            @RequestParam(name = "name") String name
    ) {
        return ResponseEntity.ok("Member info : " + name);
    }

    // 두 번째 예시
    // 속성 값만 명시
    @GetMapping("/v1/request-param/members")
    public ResponseEntity<String> getMemberDetailV1RequestParam(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        return ResponseEntity.ok("Member info : " + name + ", " + age);
    }

    // 세 번째 예시
    // 속성 명, 속성 값 모두 없을 경우 파라미터 명으로 request parameter를 찾아 바인딩
    @GetMapping("/v2/request-param/members")
    public ResponseEntity<String> getMemberDetailV2RequestParam(
            @RequestParam String name,
            @RequestParam int age
    ) {
        return ResponseEntity.ok("Member info : " + name + ", " + age);
    }

    // 네 번째 예시
    // @RequestParam 생략
    @GetMapping("/v3/request-param/members")
    public ResponseEntity<String> getMemberDetailV3RequestParam(
            String name,
            int age
    ) {
        return ResponseEntity.ok("Member info : " + name + ", " + age);
    }

    @GetMapping("/v0/model-attribute/members")
    public ResponseEntity<String> getMemberDetailV0ModelAttribute(
            @ModelAttribute MemberDto memberDto
    ) {
        return ResponseEntity.ok("Member info : " + memberDto.getMemberInfo());
    }

    // 첫 번째 예시
    // 속성 명, 속성 값 모두 명시
    @GetMapping("/v0/request-header")
    public ResponseEntity<String> getMemberDetailV0RequestHeader(
            @RequestHeader(name = "Authorization") String token
    ) {
        return ResponseEntity.ok("Token info : " + token);
    }

    // 두 번째 예시
    // 속성 값만 명시
    @GetMapping("/v1/request-header")
    public ResponseEntity<String> getMemberDetailV1RequestHeader(
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok("Token info : " + token);
    }


    // 세 번째 예시
    // 속성 명, 속성 값 모두 없을 경우 매개변수 명(authorization)으로 헤더를 찾아 바인딩
    @GetMapping("/v2/request-header")
    public ResponseEntity<String> getMemberDetailV2RequestHeader(
            @RequestHeader String authorization
    ) {
        return ResponseEntity.ok("Token info : " + authorization);
    }

    @PostMapping("/v0/request-body/members")
    public ResponseEntity<String> saveMemberV0(
            @RequestBody MemberDto memberDto
    ) {
        return ResponseEntity.ok("Member info : " + memberDto.getMemberInfo());
    }
}
