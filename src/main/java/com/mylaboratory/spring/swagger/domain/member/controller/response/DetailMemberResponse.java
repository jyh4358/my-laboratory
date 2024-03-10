package com.mylaboratory.spring.swagger.domain.member.controller.response;

import com.mylaboratory.spring.swagger.domain.member.controller.enum_type.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "멤버 상세 응답 Dto")
public class DetailMemberResponse {

    @Schema(description = "멤버 이름", requiredMode = REQUIRED)
    private String name;

    @Schema(description = "멤버 나이", requiredMode = REQUIRED)
    private int age;

    @Schema(description = "멤버 타입", requiredMode = REQUIRED)
    private MemberType memberType;
}
