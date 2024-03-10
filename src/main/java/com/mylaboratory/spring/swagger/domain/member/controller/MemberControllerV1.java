package com.mylaboratory.spring.swagger.domain.member.controller;

import com.mylaboratory.spring.swagger.domain.member.controller.enum_type.MemberType;
import com.mylaboratory.spring.swagger.domain.member.controller.request.SaveSwaggerTestRequest;
import com.mylaboratory.spring.swagger.domain.member.controller.request.SearchSwaggerTestRequest;
import com.mylaboratory.spring.swagger.domain.member.controller.response.DetailMemberResponse;
import com.mylaboratory.spring.swagger.domain.member.controller.response.ListMemberDto;
import com.mylaboratory.spring.swagger.domain.member.controller.response.ListMemberResponse;
import com.mylaboratory.spring.swagger.domain.member.controller.response.SwaggerDefaultHttpResponse;
import com.mylaboratory.spring.validation.common.BaseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// name : 명칭, desciption : 설명
@Tag(name = "멤버", description = "MemberControllerV1")
@RequestMapping("/jd/v1/member")
public class MemberControllerV1 {


    // summary : api 요약, desciption : 설명
    @Operation(summary = "회원 목록 조회", description = "회원 목록을 조회한다")
    @GetMapping("")
    // Query Parameter를 객체(ModelAttribute) 로 받는 경우
    // @ParameterObject를 사용해야 한다.
    public SwaggerDefaultHttpResponse<ListMemberResponse> getTestList(
            @ParameterObject @Valid @ModelAttribute SearchSwaggerTestRequest search
    ) {
        ListMemberDto member1 = ListMemberDto.builder()
                .name("이름1")
                .age(11)
                .memberType(MemberType.MEMBER)
                .build();

        ListMemberDto member2 = ListMemberDto.builder()
                .name("이름2")
                .age(22)
                .memberType(MemberType.ADMIN)
                .build();
        List<ListMemberDto> memberList = List.of(member1, member2);
        ListMemberResponse response = ListMemberResponse.builder()
                .content(memberList)
                .totalCount(memberList.size())
                .build();
        return new SwaggerDefaultHttpResponse<>(BaseCode.SUCCESS, response);
    }

    @Operation(summary = "테스트 상세 조회", description = "상세 조회한다.")
    @GetMapping("/{id}")
    public SwaggerDefaultHttpResponse<DetailMemberResponse> getTest(
            @Parameter @PathVariable Long id
    ) {
        System.out.println("id = " + id);
        DetailMemberResponse member = DetailMemberResponse.builder()
                .name("제이동")
                .age(35)
                .memberType(MemberType.ADMIN)
                .build();
        return new SwaggerDefaultHttpResponse<>(BaseCode.SUCCESS, member);
    }

    @Operation(summary = "테스트 저장", description = "Swagger의 test를 저장한다")
    @PostMapping("")
    public SwaggerDefaultHttpResponse<Void> saveTest(
            @Valid @RequestBody SaveSwaggerTestRequest request
    ) {
        return new SwaggerDefaultHttpResponse<>(BaseCode.SUCCESS);
    }
}
