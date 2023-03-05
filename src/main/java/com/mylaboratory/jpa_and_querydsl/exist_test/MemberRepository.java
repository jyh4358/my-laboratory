package com.mylaboratory.jpa_and_querydsl.exist_test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByName(String name);
}
