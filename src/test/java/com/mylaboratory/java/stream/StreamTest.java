package com.mylaboratory.java.stream;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class StreamTest {

    int count = 1;

    @Test
    void test1() {
        List<String> test = List.of("test1", "test2", "test3");

        test.add("test4");

        for (String s : test) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void test2() {
        Category category = new Category("parent category", null);
        Category childrenCategory = new Category("children category", category);


        Optional<Category> parent = Optional.ofNullable(category.getParent());
        CategoryDto test = new CategoryDto("test", 1L);

        Long parentCategoryId = test.getParentCategoryId();
        if (parentCategoryId == null) {
            Category category1 = new Category(test.name, null);
        } else {
            // repo.findById(parentCategoryId)
            Category category1 = new Category(test.name, category);
        }

        Optional<Long> parentCategoryId1 = Optional.ofNullable(test.getParentCategoryId());
    }

    @Test
    void stream_test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        Thread thread = new Thread(() -> {
            int threadVariable = 10;
            List<Integer> collect = list.stream()
                    .map(i -> i + count++)
                    .collect(Collectors.toList());
            for (Integer integer : collect) {
                System.out.println("thread1 - integer = " + integer);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread thread2 = new Thread(() -> {
            List<Integer> collect = list.stream()
                    .map(i -> i + count++)
                    .collect(Collectors.toList());
            for (Integer integer : collect) {
                System.out.println("thread2 - integer = " + integer);
            }
        });
        thread.start();
        thread2.start();

        int a = 0;

    }


    @Getter
    class Category{
        private String name;
        private Category parent;

        public Category(String name, Category parent) {
            this.name = name;
            this.parent = parent;
        }
    }

    @Getter
    class CategoryDto{
        private String name;
        private Long parentCategoryId;

        public CategoryDto(String name, Long parentCategoryId) {
            this.name = name;
            this.parentCategoryId = parentCategoryId;
        }
    }
}
