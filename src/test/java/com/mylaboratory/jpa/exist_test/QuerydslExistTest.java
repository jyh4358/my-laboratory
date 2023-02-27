package com.mylaboratory.jpa.exist_test;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
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
class QuerydslExistTest {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void set() {
        for (int i = 0; i < 100000; i++) {
            memberRepository.save(new Member("test" + i));
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @DisplayName("exist 성능 테스트 case1")
    public void exist_test1() {

        long start = System.currentTimeMillis();
        boolean isExist = memberRepository.existsByName("test80000");
        log.info("isExist = {}", isExist);
        long end = System.currentTimeMillis();
        log.info("case1 = {}", end - start);

    }

    @Test
    @DisplayName("exist 성능 테스트 case2")
    public void exist_test2() {

        long start = System.currentTimeMillis();
        boolean isExist = memberRepository.existsById(80000L);
        log.info("isExist = {}", isExist);
        long end = System.currentTimeMillis();

        log.info("case2 = {}", end - start);

    }

    @Test
    @DisplayName("exist 성능 테스트 case3")
    public void exist_test3() {

        long start = System.currentTimeMillis();
        Long isExist = Optional.ofNullable(queryFactory.select(QMember.member.id)
                .from(QMember.member)
                .where(QMember.member.name.eq("test80000"))
                .limit(1)
                .fetchOne()).orElse(0L);
        log.info("isExist = {}", isExist);
        long end = System.currentTimeMillis();
        log.info("case3 = {}", end - start);
    }

    @Test
    @DisplayName("exist 성능 테스트 case4")
    public void exist_test4() {

        long start = System.currentTimeMillis();
        Long isExist = Optional.ofNullable(queryFactory.select(QMember.member.id)
                .from(QMember.member)
                .where(QMember.member.name.eq("test80000"))
                .fetchFirst()).orElse(0L);
        log.info("isExist = {}", isExist);
        long end = System.currentTimeMillis();
        log.info("case4 = {}", end - start);

    }

    @Test
    @DisplayName("exist 성능 테스트 case5")
    public void exist_test5() {

        long start = System.currentTimeMillis();
        Integer isExist = Optional.ofNullable(queryFactory.selectOne()
                .from(QMember.member)
                .where(QMember.member.name.eq("test80000"))
                .fetchFirst()).orElse(0);
        log.info("isExist = {}", isExist);
        long end = System.currentTimeMillis();
        log.info("case5 = {}", end - start);
    }


}