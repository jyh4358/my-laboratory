package com.mylaboratory.jpa.exist_test;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@SpringBootTest
class QuerydslExistsTest {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    @Rollback(value = false)
//    @Order(1)
//    public void set() {
//        for (int i = 0; i < 5000000; i++) {
//            memberRepository.save(new Member("test" + i));
//        }
//        entityManager.flush();
//        entityManager.clear();

    @Test
    @DisplayName("exists 성능 테스트 case1")
    public void exists_test1() {

        long start = System.currentTimeMillis();
        Integer isExists = queryFactory
                .selectOne()
                .from(QMember.member)
                .where(JPAExpressions
                        .select(QMember.member)
                        .from(QMember.member)
                        .where(QMember.member.name.eq("test3500000"))
                        .exists()
                )
                .fetchOne();
        log.info("isExists = {}", isExists);
        long end = System.currentTimeMillis();
        log.info("case5 = {}", end - start);
    }

    @Test
    @DisplayName("exists 성능 테스트 case2")
    public void exists_test2() {

        long start = System.currentTimeMillis();
        boolean isExists = memberRepository.existsById(3500000L);
        log.info("isExists = {}", isExists);
        long end = System.currentTimeMillis();

        log.info("case2 = {}", end - start);

    }

    @Test
    @DisplayName("exists 성능 테스트 case3")
    public void exists_test3() {

        long start = System.currentTimeMillis();
        boolean isExists = memberRepository.existsByName("test3500000");
        log.info("isExists = {}", isExists);
        long end = System.currentTimeMillis();
        log.info("case1 = {}", end - start);

    }

    @Test
    @DisplayName("exists 성능 테스트 case4")
    public void exists_test4() {

        long start = System.currentTimeMillis();
        Long isExists = Optional.ofNullable(queryFactory.select(QMember.member.id)
                .from(QMember.member)
                .where(QMember.member.name.eq("test3500000"))
                .limit(1)
                .fetchOne()).orElse(0L);
        log.info("isExists = {}", isExists);
        long end = System.currentTimeMillis();
        log.info("case3 = {}", end - start);
    }

    @Test
    @DisplayName("exists 성능 테스트 case5")
    public void exists_test5() {

        long start = System.currentTimeMillis();
        Long isExists = Optional.ofNullable(queryFactory.select(QMember.member.id)
                .from(QMember.member)
                .where(QMember.member.name.eq("test3500000"))
                .fetchFirst()).orElse(0L);
        log.info("isExists = {}", isExists);
        long end = System.currentTimeMillis();
        log.info("case4 = {}", end - start);

    }

    @Test
    @DisplayName("exists 성능 테스트 case6")
    public void exists_test6() {

        long start = System.currentTimeMillis();
        Integer isExists = Optional.ofNullable(queryFactory.selectOne()
                .from(QMember.member)
                .where(QMember.member.name.eq("test3500000"))
                .fetchFirst()).orElse(0);
        log.info("isExists = {}", isExists);
        long end = System.currentTimeMillis();
        log.info("case5 = {}", end - start);
    }

}