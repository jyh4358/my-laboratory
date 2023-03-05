package com.mylaboratory.jpa_and_querydsl.projections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleConstructDto {

    private Long id;

    private String title;

    private LocalDateTime lastModifiedDate;

    private Long lastModifiedBy;
}
