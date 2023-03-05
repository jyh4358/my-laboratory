package com.mylaboratory.jpa_and_querydsl.projections;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
