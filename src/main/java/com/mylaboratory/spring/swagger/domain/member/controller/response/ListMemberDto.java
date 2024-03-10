package com.mylaboratory.spring.swagger.domain.member.controller.response;

import com.mylaboratory.spring.swagger.domain.member.controller.enum_type.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 응답 Dto")
public class ListMemberDto {

    @Schema(description = "회원 이름", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "회원 나이", requiredMode = Schema.RequiredMode.REQUIRED)
    private int age;

    @Schema(description = "회원 타입", requiredMode = Schema.RequiredMode.REQUIRED)
    private MemberType memberType;

}
