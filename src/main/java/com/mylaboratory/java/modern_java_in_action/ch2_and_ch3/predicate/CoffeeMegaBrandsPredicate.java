package com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.predicate;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Brands;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Coffee;

public class CoffeeMegaBrandsPredicate implements CoffeePredicate{
    @Override
    public boolean filter(Coffee coffee) {
        return Brands.MEGA.equals(coffee.getBrands());
    }
}
