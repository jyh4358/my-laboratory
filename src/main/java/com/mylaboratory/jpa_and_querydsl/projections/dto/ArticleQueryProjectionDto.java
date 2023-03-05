package com.mylaboratory.jpa_and_querydsl.projections.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleQueryProjectionDto {

    private Long id;

    private String title;

    private LocalDateTime lastModifiedDate;

    private Long lastModifiedBy;

    @QueryProjection
    public ArticleQueryProjectionDto(Long id, String title, LocalDateTime lastModifiedDate, Long lastModifiedBy) {
        this.id = id;
        this.title = title;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
