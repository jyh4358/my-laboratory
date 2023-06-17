package com.mylaboratory.java.modern_java_in_action.ch8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @DisplayName("리스트 팩토리로 만들어진 컬렉션에 추가 시 예외")
    @Test
    void list_factory_ex_test() {
        List<String> nameList = List.of("김유정", "가인", "최수빈");
        assertThrows(UnsupportedOperationException.class, () -> nameList.add("전지현"));
    }

    @DisplayName("리스트 컬렉션의 removeIf")
    @Test
    void list_removeIf() {
        // ** 자바 9 이전 코드 **
        // List의 특정 요소 제거
        List<String> nameList1 = new ArrayList<>();
        nameList1.add("김유정");
        nameList1.add("가인");
        nameList1.add("최수빈");

        for (String s : nameList1) {
            if (s.equals("가인")) {
                nameList1.remove("가인");
            }
        }

        nameList1.removeIf(name -> name.equals("가인"));
        assertThat(nameList1.size()).isEqualTo(2);
        assertThat(nameList1).isNotEqualTo("가인");


        // ** 자바 9 이후 코드 **
        // List의 특정 요소 제거
        List<String> nameList2 = new ArrayList<>();
        nameList2.add("김유정");
        nameList2.add("가인");
        nameList2.add("최수빈");

        nameList2.removeIf(name -> name.equals("가인"));
        assertThat(nameList2.size()).isEqualTo(2);
        assertThat(nameList2).isNotEqualTo("가인");
    }

    @Test
    void list_removeAll() {
        List<String> nameList = new ArrayList<>();
        nameList.add("김유정");
        nameList.add("가인");
        nameList.add("최수빈");


    }
}
