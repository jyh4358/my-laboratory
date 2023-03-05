package com.mylaboratory.jpa_and_querydsl.projections;

import com.mylaboratory.jpa_and_querydsl.common.dto.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Builder
    public Article(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
