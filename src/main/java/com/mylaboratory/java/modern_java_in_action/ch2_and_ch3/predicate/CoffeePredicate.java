package com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.predicate;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Coffee;

public interface CoffeePredicate {
    boolean filter(Coffee coffee);
}
