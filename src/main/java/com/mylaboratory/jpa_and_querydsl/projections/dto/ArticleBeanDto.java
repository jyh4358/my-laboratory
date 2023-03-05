package com.mylaboratory.jpa_and_querydsl.projections.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ArticleBeanDto {

    private Long id;

    private String title;

    private LocalDateTime lastModifiedDate;

    private Long lastModifiedBy;
}
