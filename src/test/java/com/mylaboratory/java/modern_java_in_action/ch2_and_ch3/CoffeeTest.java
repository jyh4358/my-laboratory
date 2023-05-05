package com.mylaboratory.java.modern_java_in_action.ch2_and_ch3;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.predicate.CoffeeLessThanPricePredicate;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.predicate.CoffeeMegaBrandsPredicate;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.predicate.CoffeePredicate;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
class CoffeeTest {

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
    void legacy_code() {
        List<Coffee> megaCoffeeList = Coffee.filterMegaCoffeeList(coffeeList);
        Assertions.assertThat(megaCoffeeList.size()).isEqualTo(2);
    }

    @Test
    void legacy_parameter_code() {

        List<Coffee> megaCoffeeList = Coffee.filterBrandsList(coffeeList, Brands.MEGA);
        Assertions.assertThat(megaCoffeeList.size()).isEqualTo(2);

        List<Coffee> starbucksCoffeeList = Coffee.filterBrandsList(coffeeList, Brands.STARBUCKS);
        Assertions.assertThat(starbucksCoffeeList.size()).isEqualTo(3);

        List<Coffee> paikdabangCoffeeList = Coffee.filterBrandsList(coffeeList, Brands.PAIKDABANG);
        Assertions.assertThat(paikdabangCoffeeList.size()).isEqualTo(2);

        List<Coffee> twosomeCoffeeList = Coffee.filterBrandsList(coffeeList, Brands.TWOSOME);
        Assertions.assertThat(twosomeCoffeeList.size()).isEqualTo(2);

        List<Coffee> ediyaCoffeeList = Coffee.filterBrandsList(coffeeList, Brands.EDIYA);
        Assertions.assertThat(ediyaCoffeeList.size()).isEqualTo(2);
    }

    @Test
    void legacy_parameter_code_2() {

        List<Coffee> coffeeList1 = Coffee.filterParameterCheckList(coffeeList, Brands.MEGA, 2000);
        List<Coffee> coffeeList2 = Coffee.filterParameterCheckList(coffeeList, null, 3000);

        for (Coffee coffee : coffeeList1) {
            log.info("filter1 : coffee = {}, {}, {}", coffee.getPrice(), coffee.getBrands().getDesc(), coffee.getCapacity());
        }
        for (Coffee coffee : coffeeList2) {
            log.info("filter2 : coffee = {}, {}, {}", coffee.getPrice(), coffee.getBrands().getDesc(), coffee.getCapacity());
        }
    }

    @Test
    void legacy_dynamic_parameter() {

        List<Coffee> filterCoffeeListByPrice = Coffee.filterCoffeeList(coffeeList, new CoffeeLessThanPricePredicate());
        List<Coffee> filterCoffeeListByMegaBrands = Coffee.filterCoffeeList(coffeeList, new CoffeeMegaBrandsPredicate());

        for (Coffee coffee : filterCoffeeListByPrice) {
            log.info("filterCoffeeListByPrice : coffee = {}, {}, {}", coffee.getPrice(), coffee.getBrands().getDesc(), coffee.getCapacity());
        }
        for (Coffee coffee : filterCoffeeListByMegaBrands) {
            log.info("filterCoffeeListByMegaBrands : coffee = {}, {}, {}", coffee.getPrice(), coffee.getBrands().getDesc(), coffee.getCapacity());
        }
    }

    @Test
    void anonymous_class() {

        List<Coffee> megaCoffeeList = Coffee.filterCoffeeList(coffeeList, new CoffeePredicate() {
            @Override
            public boolean filter(Coffee coffee) {
                return Brands.MEGA.equals(coffee.getBrands());
            }
        });

        Assertions.assertThat(megaCoffeeList.size()).isEqualTo(2);
    }

    @Test
    void lambda_filter() {

        List<Coffee> megaCoffeeList = Coffee.lambdaFilter(coffeeList, coffee -> Brands.MEGA.equals(coffee.getBrands()));

        for (Coffee coffee : megaCoffeeList) {
            log.info("coffee = {}, {}, {}", coffee.getPrice(), coffee.getBrands().getDesc(), coffee.getCapacity());
        }
    }
}