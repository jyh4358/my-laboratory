package com.mylaboratory.java.modern_java_in_action.ch4;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Brands;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Coffee;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Size;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

@Slf4j
class CoffeeStreamCollectorsTest {
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
    void collectors_counting_test() {
        Long counting = coffeeList.stream()
                .collect(Collectors.counting());
        long count = coffeeList.stream()
                .count();
        Assertions.assertThat(counting).isEqualTo(coffeeList.size());
        Assertions.assertThat(count).isEqualTo(coffeeList.size());

    }

    @Test
    void collectors_max_min_test() {
        Optional<Coffee> minOp = coffeeList.stream()
                .collect(Collectors.minBy(Comparator.comparing(Coffee::getPrice)));
        Optional<Coffee> maxOp = coffeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Coffee::getPrice)));

        Assertions.assertThat(minOp.get().getPrice()).isEqualTo(2000);
        Assertions.assertThat(maxOp.get().getPrice()).isEqualTo(5500);
    }

    @Test
    void collectors_summingInt_test() {
        Integer priceSum = coffeeList.stream()
                .collect(Collectors.summingInt(Coffee::getPrice));
        log.info("priceSum = {}", priceSum);
    }

    @Test
    void collectors_averagingInt_test() {
        Double priceAverage = coffeeList.stream()
                .collect(Collectors.averagingDouble(Coffee::getPrice));
        log.info("priceAverage = {}", priceAverage);
    }

    @Test
    void collectors_summarizingInt_test() {
        IntSummaryStatistics summary = coffeeList.stream()
                .collect(Collectors.summarizingInt(Coffee::getPrice));
        log.info("summary = {}", summary);
    }

    @Test
    void collectors_joining_test() {
        String joinBrands1 = coffeeList.stream()
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .collect(Collectors.joining());
        log.info("joinBrands1 = {}", joinBrands1);

        String joinBrands2 = coffeeList.stream()
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .distinct()
                .collect(Collectors.joining());
        log.info("joinBrands2 = {}", joinBrands2);

        String joinBrands3 = coffeeList.stream()
                .map(Coffee::getBrands)
                .map(Brands::getDesc)
                .distinct()
                .collect(Collectors.joining(", "));
        log.info("joinBrands3 = {}", joinBrands3);
    }

    @Test
    void collectors_reducing_test() {
        // 첫번째 인수 - 초기 값
        // 두번째 인수 - reduce 가 수행할 요소들
        // 세번째 인수 - reduce 수행 식
        Integer customPriceSum = coffeeList.stream()
                .collect(Collectors.reducing(0, Coffee::getPrice, (a, b) -> b >= 3000 ? a + b : a));
        log.info("customPriceSum = {}", customPriceSum);
    }

    @Test
    void collectors_groupingBy_test() {

        // 자바 8 이전 코드
        Map<Brands, List<Coffee>> coffeeMap = new HashMap<>();
        for (Coffee coffee : coffeeList) {
            List<Coffee> coffeeList = coffeeMap.getOrDefault(coffee.getBrands(), new ArrayList<>());
            if (coffeeList.isEmpty()) {
                coffeeMap.put(coffee.getBrands(), coffeeList);
            }
            coffeeList.add(coffee);
        }
        System.out.println("coffeeMap         = " + coffeeMap);

        // 자바 8 Stream 그룹화
        Map<Brands, List<Coffee>> coffeeMapByStream = coffeeList.stream()
                .collect(Collectors.groupingBy(Coffee::getBrands));
        System.out.println("coffeeMapByStream = " + coffeeMapByStream);
    }

    @Test
    void collectors_groupingBy_test2() {
        // TWOSOME 과 STARBUCKS 에 해당되는 커피의 가격이 3500원 이하인 커피가 없으므로
        // Map에 TOWSOME, STARBUCKS 인 Key가 존재하지 않게 된다.
        Map<Brands, List<Coffee>> filterGroupingMap = coffeeList.stream()
                .filter(coffee -> coffee.getPrice() < 3500)
                .collect(Collectors.groupingBy(Coffee::getBrands));

        // Map에 TOWSOME과 STARBUCKS 인 Key가 존재하며 빈 목록을 갖는다.
        Map<Brands, List<Coffee>> groupingFilterMap = coffeeList.stream()
                .collect(Collectors.groupingBy(Coffee::getBrands,
                        Collectors.filtering(coffee -> coffee.getPrice() < 3500, Collectors.toList())));
        System.out.println("filterGroupingMap = " + filterGroupingMap);
        System.out.println("groupingFilterMap = " + groupingFilterMap);
    }

    @Test
    void collectors_groupingBy_test3() {
        Map<Brands, List<Integer>> groupingMappingMap = coffeeList.stream()
                .collect(Collectors.groupingBy(Coffee::getBrands,
                        Collectors.mapping(Coffee::getCapacity, Collectors.toList())));
        System.out.println("groupingMappingMap = " + groupingMappingMap);

        final List<Dish> menu = asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        final Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> flatMappingMap = menu.stream().collect(
                groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println("flatMappingMap = " + flatMappingMap);
    }

    @Test
    void collectors_grouping_test4() {
        Map<Brands, Map<Size, List<Coffee>>> twoGroupingMap = coffeeList.stream()
                .collect(groupingBy(Coffee::getBrands,
                        groupingBy(coffee -> {
                            if (coffee.getCapacity() < 400) {
                                return Size.SMALL;
                            } else if (coffee.getCapacity() < 750) {
                                return Size.MEDIUM;
                            } else {
                                return Size.LARGE;
                            }
                        })));
        System.out.println("twoGroupingMap = " + twoGroupingMap);
    }

    @Test
    void collectors_groupingBy_test5() {
        Map<Brands, Optional<Coffee>> groupingMaxByMap = coffeeList.stream()
                .collect(groupingBy(Coffee::getBrands, maxBy(Comparator.comparing(Coffee::getPrice))));
        System.out.println("collect = " + groupingMaxByMap);

        Map<Brands, Long> groupingCountingMap = coffeeList.stream()
                .collect(groupingBy(Coffee::getBrands, counting()));
        System.out.println("groupingCountingMap = " + groupingCountingMap);
    }

    @Test
    void collectors_groupingBy_test6() {
        Map<Brands, Coffee> coffeeMap = coffeeList.stream()
                .collect(groupingBy(Coffee::getBrands,
                        collectingAndThen(maxBy(Comparator.comparing(Coffee::getPrice)), Optional::get)));
        System.out.println("coffeeMap = " + coffeeMap);
    }

    @Test
    void collectors_groupingBy_test7() {
        Map<Boolean, List<Coffee>> partitioningMap = coffeeList.stream()
                .collect(partitioningBy(coffee -> coffee.getCapacity() >= 600));
        System.out.println("partitioningMap = " + partitioningMap);
    }

    public class Dish {

        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        @Override
        public String toString() {
            return name;
        }

        public enum Type {
            MEAT,
            FISH,
            OTHER
        }
    }

}
