package com.mylaboratory.java.modern_java_in_action.ch4;

import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Brands;
import com.mylaboratory.java.modern_java_in_action.ch2_and_ch3.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
class CoffeeStreamTest {
    private List<Coffee> coffeeList = new ArrayList<>();
    private List<Trader> traders = new ArrayList<>();
    private List<Transaction> transactions= new ArrayList<>();


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
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        traders = Arrays.asList(raoul, mario, alan, brian);
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
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

    @Test
    void stream_anyMatch() {
        boolean isMega = coffeeList.stream()
                .anyMatch(coffee -> coffee.getBrands().equals(Brands.MEGA));
    }

    @Test
    void stream_allMatch() {
        boolean allMatchCheck = coffeeList.stream()
                .allMatch(coffee -> coffee.getPrice() >= 2000);
    }

    @Test
    void stream_noneMatch() {
        boolean noneMatchCheck = coffeeList.stream()
                .noneMatch(coffee -> coffee.getPrice() < 2000);
    }

    @Test
    void stream_findAny() {
        Optional<Coffee> coffeeOp = coffeeList.stream()
                .filter(coffee -> coffee.getBrands().equals(Brands.MEGA))
                .findAny();
        Coffee coffee = coffeeOp.get();
        System.out.println("coffee = " + coffee);
    }

    @Test
    void stream_reduce() {
        List<Integer> numberList = Arrays.asList(4, 5, 3, 9);

        int sum = 0;
        for (Integer number : numberList) {
            sum += number;
        }

        int reduceSum = numberList.stream().reduce(0, (a, b) -> a + b);
//        int reduceSum = numberList.stream().reduce(0, Integer::sum);
        log.info("sum = {}, reduceSum = {}", sum, reduceSum);
    }

    @Test
    void stream_reduce_no_init() {
        List<Integer> numberList = Arrays.asList(4, 5, 3, 9);

        Optional<Integer> reduceSumOp = numberList.stream().reduce(Integer::sum);
        int reduceSum = reduceSumOp.orElse(0);
        log.info("reduceSum = {}", reduceSum);
    }

    @Test
    void stream_ex_1() {
        // 2011년에 일어난 모든 트랜잭션을 찾아서 값을 오름차순으로 정렬하시오.
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.year == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        Assertions.assertThat(tr2011.size()).isEqualTo(2);
    }

    @Test
    void stream_ex_2() {
        // 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> cities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertThat(cities.size()).isEqualTo(2);
    }

    @Test
    void stream_ex_3() {
        // Cambridge 에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        Assertions.assertThat(traders.size()).isEqualTo(3);
    }

    @Test
    void stream_ex_4() {
        // 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        String traderStr = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining());

        log.info("traderStr = {}", traderStr);
    }

    @Test
    void stream_ex_5() {
        // Milan 에 거래자가 있는가?
        boolean isMilan = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(city -> city.equals("Milan"));

        Assertions.assertThat(isMilan).isTrue();
    }

    @Test
    void stream_ex_6() {
        // Cambridge에 거주하는 거래자의 모든 트랜잭션 값을 출력하시오
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    void stream_ex_7() {
        // 전체 트랜잭션 중 최댓값, 최소값은 얼마인가?
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        Optional<Transaction> maxValue2 = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));

        Optional<Integer> minValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        Optional<Transaction> minValue2 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        log.info("max = {}, {}, min = {}, {}", maxValue, maxValue2, minValue, minValue2);
    }

    @Test
    void primitive_stream() {

        int priceSum = coffeeList.stream()
                .map(Coffee::getPrice)  // 박싱 비용 발생!
                .reduce(0, (a, b) -> a + b);

        int priceSum2 = coffeeList.stream()
                .mapToInt(Coffee::getPrice) // IntStream 반환, 박싱 비용 발생하지 않는다.
                .sum();
    }

    @Test
    void primitive_stream_boxed() {
        IntStream intStream = coffeeList.stream()
                .mapToInt(Coffee::getPrice);
        Stream<Integer> stream = intStream.boxed();
    }

    @Test
    void primitive_stream_op() {
        OptionalInt minPriceOp = coffeeList.stream()
                .mapToInt(Coffee::getPrice)
                .min();
    }


    class Trader {
        private String name;
        private String city;

        public Trader(String n, String c) {
            name = n;
            city = c;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String newCity) {
            city = newCity;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + (name == null ? 0 : name.hashCode());
            hash = hash * 31 + (city == null ? 0 : city.hashCode());
            return hash;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof Trader)) {
                return false;
            }
            Trader o = (Trader) other;
            boolean eq = Objects.equals(name, o.getName());
            eq = eq && Objects.equals(city, o.getCity());
            return eq;
        }

        @Override
        public String toString() {
            return String.format("Trader:%s in %s", name, city);
        }
    }

    class Transaction {

        private Trader trader;
        private int year;
        private int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + (trader == null ? 0 : trader.hashCode());
            hash = hash * 31 + year;
            hash = hash * 31 + value;
            return hash;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof Transaction)) {
                return false;
            }
            Transaction o = (Transaction) other;
            boolean eq = Objects.equals(trader, o.getTrader());
            eq = eq && year == o.getYear();
            eq = eq && value == o.getValue();
            return eq;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("{%s, year: %d, value: %d}", trader, year, value);
        }

    }
}
