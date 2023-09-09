package com.mylaboratory.java.completable_future;

public class ApiCall {
    public static String apiACall() {
        // API A 응답 시간 2초 설정
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "API A result";
    }

    public static String apiBCall() {
        // API B 응답 시간 2초 설정
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "API B result";
    }
}
