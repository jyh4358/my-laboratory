package com.mylaboratory.jpa_and_querydsl.projections.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleFieldsDto {

    private Long id;

    private String title;

    private LocalDateTime lastModifiedDate;

    private Long lastModifiedBy;
}
