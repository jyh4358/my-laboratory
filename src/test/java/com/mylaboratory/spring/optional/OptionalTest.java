package com.mylaboratory.spring.optional;

import com.mylaboratory.jpa_and_querydsl.exist_test.Member;
import com.mylaboratory.jpa_and_querydsl.exist_test.MemberRepository;
import com.mylaboratory.spring.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("local")
public class OptionalTest {

    @Autowired
    private MemberRepository memberRepository;

    private final Member DEFAULT_MEMBER = new Member("Default Member");

    @DisplayName("Optional에 null을 할당하지 말아라")
    @Test
    void optional_null_test() {
        Long memberId = 10L;
        Optional<Member> memberOp = findByIdAvoidMethod(memberId);
//        Optional<Member> memberOp = findByIdPreferMethod(memberId);
        if (memberOp.isPresent()) { // ❗ NullPointerException 발생
        }
    }

    @DisplayName("Optional 객체의 값 존재유무를 확인해라 - avoid")
    @Test
    void optional_get_test1() {
        Optional<Member> memberOp = memberRepository.findById(100L);
        String name = memberOp.get().getName(); // ❗ NoSuchElementException 발생
    }

    @DisplayName("Optional 객체의 값 존재유무를 확인해라 - prefer")
    @Test
    void optional_get_test2() throws NotFoundException {
        // case1 - 해당 Member를 찾지 못했을 경우 예외처리
        Member member = memberRepository.findById(100L).orElseThrow(NotFoundException::new);

        // case2 - Member가 있을 경우와 Member가 없을 경우 분기처리
        Optional<Member> memberOp = memberRepository.findById(100L);
        // Optional 객체의 Member가 존재하는 경우
        if (memberOp.isPresent()) {
        }
        // Optional 객체의 Member가 존재하지 않는 경우
        if (memberOp.isEmpty()) {
        }
    }

    @DisplayName("Optional.orElse()를 사용하는 경우 이미 생성된 기본 값(객체)를 제공해라 - avoid")
    @Test
    void optional_orElse_test1() {

        // ❗ memberId = 100L 인 Member가 존재해도 new Member("Default Member")가 생성이 된다.
        Member member = memberRepository.findById(100L).orElse(new Member("Default Member"));
    }

    @DisplayName("Optional.orElse()를 사용하는 경우 이미 생성된 기본 값(객체)를 제공해라 - prefer")
    @Test
    void optional_orElse_test2() {

        // ✨ Optional.orElse 메서드는 값의 존재 유무와 상관없이 항상실행되므로
        // Optional에 값이 없을 경우 항상 같은 객체를 반환해야한다면 미리 만들어 사용해야 한다.
        Member member = memberRepository.findById(100L).orElse(DEFAULT_MEMBER);
    }

    @DisplayName("Optional 객체에 값이 없을 경우 매번 새로운 객체를 생성해야 한다면 Optional.orElseGet()을 사용해라")
    @Test
    void optional_orElseGet_test1() {

        // ✨ Optional.orElseGet() 메서드는 Optional 객체의 값이 없을 경우에만 실행이 된다.
        Member member = memberRepository.findById(100L).orElseGet(() -> new Member("Default Member"));

    }

    @DisplayName("Optional 객체에 값이 있는 경우에 특정 로직 실행")
    @Test
    void optional_ifPresent_test() {
        memberRepository.findById(10L)
                .ifPresent(member -> System.out.println("member.getName() = " + member.getName()));
    }

    @DisplayName("ifPresent - get 패턴은 orElse나 orElseXXX를 사용해라 - avoid")
    @Test
    void optional_api_test1() throws NotFoundException {
        Optional<Member> memberOp = memberRepository.findById(10L);

        if (memberOp.isPresent()) {
            Member member = memberOp.get();
            System.out.println("member.getName() = " + member.getName());
        } else {
            throw new NotFoundException();
        }
    }

    @DisplayName("ifPresent - get 패턴은 orElse나 orElseXXX를 사용해라 - prefer")
    @Test
    void optional_api_test2() throws NotFoundException {
        Member member = memberRepository.findById(10L).orElseThrow(NotFoundException::new);
        System.out.println("member.getName() = " + member.getName());
    }

    @DisplayName("Optional 객체의 값 비교에는 Optional.equals()를 사용해라")
    @Test
    void optional_equals_test() {
        Optional<Member> memberOp1 = Optional.of(new Member(1L, "test"));
        Optional<Member> memberOp2 = Optional.of(new Member(1L, "test"));
        boolean equals = memberOp1.equals(memberOp2);
        System.out.println("equals = " + equals);
    }


    private Optional<Member> findByIdAvoidMethod(Long memberId) {
        Member member = null; // find Member from DB
        if (member == null) {
            return null;     // ❗ null 반환
        }
        return Optional.ofNullable(member);
    }

    private Optional<Member> findByIdPreferMethod(Long memberId) {
        Member member = null; // find Member from DB
        if (member == null) {
            return Optional.empty(); // ✨ null이 아닌 값이 없는 Optional 객체 반환
        }
        return Optional.ofNullable(member);
    }
}
