package com.mylaboratory.java.modern_java_in_action.ch2_and_ch3;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.predicate.CoffeePredicate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
@ToString
@NoArgsConstructor
public class Coffee {
    private int price;
    private int capacity;
    private Brands brands;

    public Coffee(int price, int capacity, Brands brands) {
        this.price = price;
        this.capacity = capacity;
        this.brands = brands;
    }

    public static List<Coffee> filterMegaCoffeeList(List<Coffee> coffeeList) {
        List<Coffee> filterList = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (Brands.MEGA.equals(coffee.getBrands())) {
                filterList.add(coffee);
            }
        }
        return filterList;
    }

    public static List<Coffee> filterBrandsList(List<Coffee> coffeeList, Brands brands) {
        List<Coffee> filterList = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (brands.equals(coffee.getBrands())) {
                filterList.add(coffee);
            }
        }
        return filterList;
    }

    public static List<Coffee> filterPriceLeList(List<Coffee> coffeeList, int price) {
        List<Coffee> filterList = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (price >= coffee.getPrice()) {
                filterList.add(coffee);
            }
        }
        return filterList;
    }

    public static List<Coffee> filterParameterCheckList(List<Coffee> coffeeList, Brands brands, int price) {
        List<Coffee> filterList = new ArrayList<>();
        boolean isBrands = !ObjectUtils.isEmpty(brands) ? true : false;
        boolean isPrice = price > 0 ? true : false;
        for (Coffee coffee : coffeeList) {
            if (isBrands && isPrice) { // 브랜드와 가격 모두 필터링 할 경우
                if (brands.equals(coffee.getBrands()) && price >= coffee.getPrice()) {
                    filterList.add(coffee);
                }
            } else if (isBrands) { // 브랜드만 필터링 할 경우
                if (brands.equals(coffee.getBrands())) {
                    filterList.add(coffee);
                }
            } else if (isPrice){ // 가격만 필터링 할 경우
                if (price >= coffee.getPrice()) {
                    filterList.add(coffee);
                }
            } else { // 필터링을 안할 경우
                filterList.add(coffee);
            }
        }
        return filterList;
    }

    public static List<Coffee> filterCoffeeList(List<Coffee> coffeeList, CoffeePredicate p) {
        List<Coffee> filterList = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (p.filter(coffee)) {
                filterList.add(coffee);
            }
        }
        return filterList;
    }

    public static List<Coffee> lambdaFilter(List<Coffee> coffeeList, Predicate<Coffee> p) {
        List<Coffee> filterList = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (p.test(coffee)) {
                filterList.add(coffee);
            }
        }
        return filterList;
    }
}
