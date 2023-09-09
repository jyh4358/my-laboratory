package com.mylaboratory.java.completable_futrue;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class CompletableFutureTest {

    @Test
    void runAsync_test() throws ExecutionException, InterruptedException {
        // 비동기 작업 구현 및 실행
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            // 비동기 작업 로직
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Async Thread : " + Thread.currentThread().getName() + "작업 완료");
        });

        // 비동기 작업이 완료 될 때까지 기다리지 않고 Main 로직 수행
        System.out.println("Main Thread : " + Thread.currentThread().getName() + "작업 완료");

        // 비동기 작업이 완료될 때까지 블록
        runAsyncFuture.get();
    }

    @Test
    void supplyAsync_test() throws ExecutionException, InterruptedException {
        // 비동기 작업 구현 및 실행
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            // 비동기 작업 로직
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Async Thread : " + Thread.currentThread().getName() + "작업 완료";
        });

        // 비동기 작업이 완료 될 때까지 기다리지 않고 Main 로직 수행
        System.out.println("Main Thread : " + Thread.currentThread().getName() + "작업 완료");

        // 비동기 작업이 완료될 때까지 블록하고 결과 또는 예외를 반환
        String result = supplyAsyncFuture.get();
        System.out.println(result);
    }
}
