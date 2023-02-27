package com.mylaboratory.jpa.exist_test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByName(String name);
}
