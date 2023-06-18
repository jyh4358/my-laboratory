package com.mylaboratory.java.modern_java_in_action.ch8;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class CollectionTest {

    @DisplayName("List 팩토리 사용")
    @Test
    void list_factory_test() {
        List<String> coffeeList = List.of("메가커피", "스타벅스", "빽다방");
        System.out.println("coffeeList = " + coffeeList);
    }

    @DisplayName("Set 팩토리 사용")
    @Test
    void set_factory_test() {
        Set<String> coffeeSet = Set.of("메가커피", "스타벅스", "빽다방");
        System.out.println("coffeeSet = " + coffeeSet);
    }

    @DisplayName("Map 팩토리 사용")
    @Test
    void map_factory_test() {
        Map<String, Integer> coffeeOfMap = Map.of("메가커리", 1500, "스타벅스", 3500, "백다방", 2000);
        Map<String, Integer> coffeOfEntiriesMap = Map.ofEntries(
                Map.entry("메가커리", 1500),
                Map.entry("스타벅스", 3500),
                Map.entry("백다방", 2000)
        );
        System.out.println("coffeeOfMap = " + coffeeOfMap);
        System.out.println("coffeOfEntiriesMap = " + coffeOfEntiriesMap);
    }

    @DisplayName("팩토리로 만들어진 컬렉션에 추가 시 예외")
    @Test
    void list_factory_ex_test1() {
        List<String> coffeeList = List.of("메가커피", "스타벅스", "빽다방");
        assertThrows(UnsupportedOperationException.class, () -> coffeeList.add("이디야"));
    }

    @DisplayName("팩토리로 만들어진 컬렉션에 요소 삭제 시 예외")
    @Test
    void list_factory_ex_test2() {
        List<String> coffeeList = List.of("메가커피", "스타벅스", "빽다방");
        assertThrows(UnsupportedOperationException.class, () -> coffeeList.remove("스타벅스"));
    }

    @DisplayName("팩토리로 만들어진 컬렉션에 요소 수정 시 예외")
    @Test
    void list_factory_ex_test3() {
        List<String> coffeeList = List.of("메가커피", "스타벅스", "빽다방");
        assertThrows(UnsupportedOperationException.class, () -> coffeeList.set(1, "이디야"));
    }

    @DisplayName("팩토리의 파라미터로 null을 전달 시 예외")
    @Test
    void list_factory_ex_test4() {
        assertThrows(NullPointerException.class, () -> List.of("메가커피", "스타벅스", "빽다방", null));
    }

    @DisplayName("컬렉션에서 제공하는 메서드의 파라미터로 null을 전달 시 예외")
    @Test
    void list_factory_ex_test5() {
        List<String> coffeeList = List.of("메가커피", "스타벅스", "빽다방");
        assertThrows(NullPointerException.class, () -> coffeeList.contains(null));
    }


    @DisplayName("리스트 컬렉션의 removeIf")
    @Test
    void list_removeIf() {
        // ** 자바 9 이전 코드 **
        // List의 특정 요소 제거
        List<String> coffeeList1 = new ArrayList<>();
        coffeeList1.add("메거커피");
        coffeeList1.add("스타벅스");
        coffeeList1.add("빽다방");

        for (String s : coffeeList1) {
            if (s.equals("스타벅스")) {
                coffeeList1.remove("스타벅스");
            }
        }

        assertThat(coffeeList1.size()).isEqualTo(2);
        assertThat(coffeeList1).isNotEqualTo("스타벅스");


        // ** 자바 9 이후 코드 **
        // List의 특정 요소 제거
        List<String> coffeeList2 = new ArrayList<>();
        coffeeList2.add("메거커피");
        coffeeList2.add("스타벅스");
        coffeeList2.add("빽다방");

        coffeeList2.removeIf(name -> name.equals("스타벅스"));
        assertThat(coffeeList2.size()).isEqualTo(2);
        assertThat(coffeeList2).isNotEqualTo("스타벅스");
    }

    @Test
    void list_replaceAll() {
        List<String> coffeeList = new ArrayList<>();
        coffeeList.add("메가커피");
        coffeeList.add("스타벅스");
        coffeeList.add("빽다방");

        coffeeList.replaceAll(coffee -> coffee + " 가맹점");
        System.out.println("coffeeList = " + coffeeList);
    }

    @DisplayName("Map forEach 메서드를 사용한 코드")
    @Test
    void map_forEach() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2000);

        coffeeMap.forEach((coffee, price) -> System.out.println("coffeeMap = " + coffee + ":" + price));
    }

    @DisplayName("Map forEach 메서드 사용하기 이전 코드")
    @Test
    void map_for_each() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2000);

        for (Map.Entry<String, Integer> entry : coffeeMap.entrySet()) {
            String coffee = entry.getKey();
            Integer price = entry.getValue();
            System.out.println("coffeeMap = " + coffee + ":" + price);
        }
    }

    @DisplayName("Map key, value 중심으로 정렬")
    @Test
    void map_comparing_by_key() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2100);

        // 기존 정렬 순서
        System.out.println("coffeeMap = " + coffeeMap);

        // key 기준으로 정렬
        coffeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        // value 기준으로 정렬
        coffeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(System.out::println);
    }

    @DisplayName("Map - getOrDefault 메서드")
    @Test
    void map_getOrDefault_test() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2100);

        // Map key에 메가커피가 있으므로 2000이 반환된다.
        Integer megaCoffePrice = coffeeMap.getOrDefault("메가커피", 1500);
        System.out.println("megaCoffePrice = " + megaCoffePrice);

        // Map key에 이디야가 없으므로 두 번째 인수로 받은 값인 3200이 반환된다.
        Integer ediyaPrice = coffeeMap.getOrDefault("이디야", 3200);
        System.out.println("ediyaPrice = " + ediyaPrice);

        // getOrDefault 를 사용하지 않은 코드는 다음과 같다.
        Integer ediyaPriceByGet = coffeeMap.get("이디야");
        if (ObjectUtils.isEmpty(ediyaPriceByGet)) {
            ediyaPriceByGet = 3200;
        }
        System.out.println("ediyaPriceByGet = " + ediyaPriceByGet);
    }

    @DisplayName("Map - 계산 관련 메서드")
    @Test
    void map_computeIfAbsent() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2100);

        // key(이디야)가 Map에 없으므로 Map에 추가해준다.
        // coffeeMap.put("이디야", 3200) 실행
        coffeeMap.computeIfAbsent("이디야", coffee -> 3200);

        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - 계산 관련 메서드2")
    @Test
    void map_computeIfAbsent2() {
        Map<String, List<Integer>> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", new ArrayList<>());
        coffeeMap.put("스타벅스", new ArrayList<>());
        coffeeMap.put("빽다방", new ArrayList<>());
        coffeeMap.get("메가커피").add(2000);

        // key(이디야)가 Map에 없으므로 Map에 추가해준다.
        // coffeeMap.put("이디야", new ArrayList<>())
        // coffeMap.get("이디야").add(3200) 실행한 것과 같다.
        coffeeMap.computeIfAbsent("이디야", coffee -> new ArrayList<>())
                .add(3200);

        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - 계산 관련 메서드3")
    @Test
    void map_computeIfPresent1() {
        Map<String, List<Integer>> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", new ArrayList<>());
        coffeeMap.put("스타벅스", null);
        coffeeMap.put("빽다방", new ArrayList<>());

        List<Integer> addList = new ArrayList<>();
        addList.add(2000);
        addList.add(3000);

        // 메가커피의 value가 null이 아니므로 addList가 추가된다.
        coffeeMap.computeIfPresent("메가커피", (coffee, price) -> addList);
        // 스타벅스의 value가 null이므로 해당 메서드는 실행되지 않는다.
        coffeeMap.computeIfPresent("스타벅스", (coffee, price) -> addList);

        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - 계산 관련 메서드4")
    @Test
    void map_compute1() {
        Map<String, List<Integer>> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", new ArrayList<>());
        coffeeMap.put("스타벅스", null);
        coffeeMap.put("빽다방", new ArrayList<>());

        List<Integer> addList = new ArrayList<>();
        addList.add(2000);
        addList.add(3000);

        // 실행 된다.
        coffeeMap.compute("메가커피", (coffee, price) -> addList);
        // 실행 된다.
        coffeeMap.compute("스타벅스", (coffee, price) -> addList);

        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - remove")
    @Test
    void map_remove() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2100);

        // 메가커피 key가 존재하므로 제거
        coffeeMap.remove("메가커피");
        // 스타벅스 key가 존재하고, value 값이 동일하므로 제거
        coffeeMap.remove("스타벅스", 3500);
        // 빽다방 key가 존재하지만 value 값이 동일하지 않으므로 제거되지 않는다.
        coffeeMap.remove("빽다방", 2200);

        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - replaceAll")
    @Test
    void map_replaceAll() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2100);

        coffeeMap.replaceAll((coffee, price) -> price + 500);
        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - replace")
    @Test
    void map_replace() {
        Map<String, Integer> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", 2000);
        coffeeMap.put("스타벅스", 3500);
        coffeeMap.put("빽다방", 2100);

        // 메가커피인 key를 찾아 value를 2500으로 변경
        coffeeMap.replace("메가커피", 2500);
        System.out.println("coffeeMap = " + coffeeMap);

        // 스타벅스인 key를 찾아 value가 3000인 경우 4000으로 변경
        // oldValue가 3000이기 때문에 실행되지 않는다.
        coffeeMap.replace("스타벅스", 3000, 4000);
        System.out.println("coffeeMap = " + coffeeMap);

        // 스타벅스인 key를 찾아 value가 3000인 경우 4000으로 변경
        // value가 3500이기 때문에 실행된다.
        coffeeMap.replace("스타벅스", 3500, 4000);
        System.out.println("coffeeMap = " + coffeeMap);
    }

    @DisplayName("Map - merge")
    @Test
    void map_merge() {
        Map<String, List<Integer>> coffeeMap = new HashMap<>();
        coffeeMap.put("메가커피", new ArrayList<>());
        coffeeMap.get("메가커피").add(2000);
        coffeeMap.put("스타벅스", new ArrayList<>());
        coffeeMap.get("스타벅스").add(3500);
        coffeeMap.put("빽다방", new ArrayList<>());
        coffeeMap.get("빽다방").add(2100);

        Map<String, List<Integer>> coffeeMap2 = new HashMap<>();
        coffeeMap2.put("메가커피", new ArrayList<>());
        coffeeMap2.get("메가커피").add(3000);
        coffeeMap2.put("스타벅스", new ArrayList<>());
        coffeeMap2.get("스타벅스").add(4500);

        coffeeMap.forEach((k, v) -> {
            coffeeMap2.merge(k, v, (price1, price2) -> {
                System.out.println("price1 = " + price1);
                System.out.println("price2 = " + price2);
                price1.addAll(price2);
                return price1;
            });
        });

        System.out.println("coffeeMap2 = " + coffeeMap2);
    }
}
