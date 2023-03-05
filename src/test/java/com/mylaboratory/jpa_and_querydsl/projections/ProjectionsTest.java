package com.mylaboratory.jpa_and_querydsl.projections;

import com.mylaboratory.jpa_and_querydsl.projections.dto.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.mylaboratory.jpa_and_querydsl.projections.QArticle.article;

@SpringBootTest
@Transactional
class ProjectionsTest {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;



    @DisplayName("Projections.bean 사용")
    @Test
    public void projections_bean() {
        Article savedArticle = articleRepository.save(Article.builder()
                .title("타이틀 테스트")
                .contents("내용 테스트")
                .build());

        ArticleBeanDto articleBeanDto = queryFactory.select(Projections.bean(ArticleBeanDto.class,
                        article.id,
                        article.title,
                        article.lastModifiedBy,
                        article.lastModifiedDate
                ))
                .from(article)
                .where(article.id.eq(savedArticle.getId()))
                .fetchOne();
        Assertions.assertThat(articleBeanDto.getId()).isEqualTo(savedArticle.getId());
        Assertions.assertThat(articleBeanDto.getTitle()).isEqualTo(savedArticle.getTitle());
        Assertions.assertThat(articleBeanDto.getLastModifiedBy()).isEqualTo(savedArticle.getLastModifiedBy());
    }

    @DisplayName("Projections.fields 사용")
    @Test
    public void projections_field() {
        Article savedArticle = articleRepository.save(Article.builder()
                .title("타이틀 테스트")
                .contents("내용 테스트")
                .build());

        ArticleFieldsDto articleFieldsDto = queryFactory.select(Projections.fields(ArticleFieldsDto.class,
                        article.id.as("id"),
                        article.title.as("title"),
                        article.lastModifiedBy.as("lastModifiedBy"),
                        article.lastModifiedDate.as("lastModifiedDate")
                ))
                .from(article)
                .where(article.id.eq(savedArticle.getId()))
                .fetchOne();
        Assertions.assertThat(articleFieldsDto.getId()).isEqualTo(savedArticle.getId());
        Assertions.assertThat(articleFieldsDto.getTitle()).isEqualTo(savedArticle.getTitle());
        Assertions.assertThat(articleFieldsDto.getLastModifiedBy()).isEqualTo(savedArticle.getLastModifiedBy());
    }

    @DisplayName("Projections.constructor 사용")
    @Test
    public void projections_constructor() {
        Article savedArticle = articleRepository.save(Article.builder()
                .title("타이틀 테스트")
                .contents("내용 테스트")
                .build());

        ArticleConstructDto articleConstructDto = queryFactory.select(Projections.constructor(ArticleConstructDto.class,
                        article.id.as("id"),
                        article.title.as("title"),
                        article.lastModifiedDate.as("lastModifiedDate"),
                        article.lastModifiedBy.as("lastModifiedBy")
                ))
                .from(article)
                .where(article.id.eq(savedArticle.getId()))
                .fetchOne();
        Assertions.assertThat(articleConstructDto.getId()).isEqualTo(savedArticle.getId());
        Assertions.assertThat(articleConstructDto.getTitle()).isEqualTo(savedArticle.getTitle());
        Assertions.assertThat(articleConstructDto.getLastModifiedBy()).isEqualTo(savedArticle.getLastModifiedBy());
    }

    @DisplayName("@QueryProjection 사용")
    @Test
    public void query_projection_annotation() {
        Article savedArticle = articleRepository.save(Article.builder()
                .title("타이틀 테스트")
                .contents("내용 테스트")
                .build());

        ArticleQueryProjectionDto articleQueryProjectionDto = queryFactory.select(new QArticleQueryProjectionDto(
                        article.id,
                        article.title,
                        article.lastModifiedDate,
                        article.lastModifiedBy)
                )
                .from(article)
                .where(article.id.eq(savedArticle.getId()))
                .fetchOne();
        Assertions.assertThat(articleQueryProjectionDto.getId()).isEqualTo(savedArticle.getId());
        Assertions.assertThat(articleQueryProjectionDto.getTitle()).isEqualTo(savedArticle.getTitle());
        Assertions.assertThat(articleQueryProjectionDto.getLastModifiedBy()).isEqualTo(savedArticle.getLastModifiedBy());
    }

}