package com.mylaboratory.jpa_and_querydsl.projections.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mylaboratory.jpa_and_querydsl.projections.dto.QArticleQueryProjectionDto is a Querydsl Projection type for ArticleQueryProjectionDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QArticleQueryProjectionDto extends ConstructorExpression<ArticleQueryProjectionDto> {

    private static final long serialVersionUID = 1049657338L;

    public QArticleQueryProjectionDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDateTime> lastModifiedDate, com.querydsl.core.types.Expression<Long> lastModifiedBy) {
        super(ArticleQueryProjectionDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDateTime.class, long.class}, id, title, lastModifiedDate, lastModifiedBy);
    }

}

