package com.mylaboratory.spring.swagger.domain.member.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.Builder.Default;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 목록 응답 Dto")
public class ListMemberResponse {

    @Default
    @Schema(description = "회원 목록")
    private List<ListMemberDto> content = new ArrayList<>();

    @Schema(description = "총 엘리먼트 수")
    private long totalCount;
}
