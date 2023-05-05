package com.mylaboratory.java.modern_java_in_action.ch2_and_ch3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Brands {
    MEGA("메가커피"),
    STARBUCKS("스타벅스"),
    PAIKDABANG("빽다방"),
    TWOSOME("투썸플레이스"),
    EDIYA("이디야")
    ;

    private String desc;
}
