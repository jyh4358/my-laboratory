package com.mylaboratory.java.modern_java_in_action.ch4;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Brands;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class CoffeeStreamTest {
    private List<Coffee> coffeeList = new ArrayList<>();

    @BeforeEach
    void init() {
        coffeeList = Arrays.asList(
                new Coffee(2000, 680, Brands.MEGA),
                new Coffee(3000, 1000, Brands.MEGA),
                new Coffee(4500, 355, Brands.STARBUCKS),
                new Coffee(5000, 473, Brands.STARBUCKS),
                new Coffee(5500, 592, Brands.STARBUCKS),
                new Coffee(2000, 625, Brands.PAIKDABANG),
                new Coffee(3000, 946, Brands.PAIKDABANG),
                new Coffee(4500, 355, Brands.TWOSOME),
                new Coffee(5000, 414, Brands.TWOSOME),
                new Coffee(3200, 420, Brands.EDIYA),
                new Coffee(4200, 650, Brands.EDIYA)
        );
    }

    @Test
    void stream_test() {
        List<String> brandList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() < 3000)
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("brandList = " + brandList);
    }

    @Test
    void stream_filter() {
        List<Coffee> filterList = coffeeList.stream()
                .filter(coffee -> coffee.getBrands().equals(Brands.MEGA))
                .collect(Collectors.toList());
    }

    @Test
    void stream_distinct() {
        List<String> filterList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() <= 3000)
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .collect(Collectors.toList());
        System.out.println("filterList = " + filterList);

        List<String> distinctList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() <= 3000)
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctList = " + distinctList);
    }

    @Test
    void stream_predicate_slicing() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10);
        List<Integer> filterList = list.stream()
                .filter(i -> {
                    System.out.println("filter i = " + i);
                    return i < 5;
                })
                .collect(Collectors.toList());
        System.out.println("filterList = " + filterList);

        List<Integer> takeWhileList = list.stream()
                .takeWhile(i -> {
                    System.out.println("takeWhile i = " + i);
                    return i < 5;
                })
                .collect(Collectors.toList());
        System.out.println("takeWhileList = " + takeWhileList);

        List<Integer> dropWhileList = list.stream()
                .dropWhile(i -> {
                    System.out.println("dropWhile i = " + i);
                    return i < 5;
                })
                .collect(Collectors.toList());
        System.out.println("dropWhileList = " + dropWhileList);
    }

    @Test
    void stream_slicing_limit() {
        List<Coffee> limitList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() <= 3000)
                .limit(2)
                .collect(Collectors.toList());
    }

    @Test
    void stream_slicing_skip() {
        List<Integer> filterList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() <= 4000)
                .map(Coffee::getPrice)
                .collect(Collectors.toList());
        System.out.println("filterList = " + filterList);

        List<Integer> limitList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() <= 4000)
                .map(Coffee::getPrice)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("limitList = " + limitList);

        List<Integer> skipList = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() <= 4000)
                .map(Coffee::getPrice)
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("skipList = " + skipList);
    }

    @Test
    void stream_map() {
        List<String> mapList = coffeeList.stream()
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .collect(Collectors.toList());
        System.out.println("mapList = " + mapList);
    }

    @Test
    void stream_flatMap() {
        List<String> strings = Arrays.asList("Hello", "world");
        List<String[]> mapList = strings.stream()
                .map(s -> s.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("mapList = " + mapList);

        List<String> flatMapList = strings.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("flatMapList = " + flatMapList);
    }
    @Test
    void stream_flatMap_2() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> pairs = number1.stream()
                .flatMap(i -> number2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        List<int[]> pairs2 = number1.stream()
                .flatMap(i -> number2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());


    }
}
