package com.mylaboratory.java.time_and_date_api;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

@Slf4j
public class TimeAndDateApiTest {

    @DisplayName("LocalTime 정적 팩토리 메서드")
    @Test
    void create_api() {
        // now 사용
        LocalTime localTime1 = LocalTime.now();
        log.info("localTime1 = {}", localTime1);

        // of 사용
        LocalTime localTimeOf1 = LocalTime.of(13, 30); // 13:30:00
        LocalTime localTimeOf2 = LocalTime.of(13, 30, 12);
        LocalTime localTimeOf3 = LocalTime.of(13, 30, 12, 100);
        log.info("localTimeOf1 = {}", localTimeOf1);
        log.info("localTimeOf2 = {}", localTimeOf2);
        log.info("localTimeOf3 = {}", localTimeOf3);

        // parse 사용
        LocalTime localTime = LocalTime.parse("13 30 00", DateTimeFormatter.ofPattern("HH mm ss"));
        log.info("localTime = {}", localTime);


        // from 사용
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime3 = LocalTime.from(localDateTime);
        log.info("localTime3 = {}", localTime3);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                2023, 8, 15,
                15, 20, 30, 0,
                ZoneId.systemDefault());
        LocalTime localTime4 = LocalTime.from(zonedDateTime);
        log.info("localTime4 = {}", localTime4);

        // ofInstant 사용
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        log.info("now = {}", now);
        log.info("zoneId = {}", zoneId);
        LocalTime localTime5 = LocalTime.ofInstant(Instant.parse("2023-08-15T16:53:44.770Z"), zoneId);
        log.info("localTime5 = {}", localTime5);

        ZoneId seoulZone = ZoneId.of("Asia/Seoul");         // 서울 시간대
        ZoneId newYorkZone = ZoneId.of("America/New_York"); // 뉴욕 시간대

        LocalTime localTime6 = LocalTime.ofInstant(now, newYorkZone); // 특정 시간대의 시각
        log.info("localTime6 = {}", localTime6);

        // ofNanoOfDay 사용
        long nanoOfDay = 5_000_000_000L; // 5초에 해당하는 나노초
        LocalTime localTime7 = LocalTime.ofNanoOfDay(nanoOfDay);
        log.info("localTime7 = {}", localTime7); // 출력: 00:00:05

        // ofSecondOfDay 사용
        long secondOfDay = 36_500L; // 10시 8분 20초에 해당하는 초
        LocalTime localTime8 = LocalTime.ofSecondOfDay(secondOfDay);
        log.info("localTime8 = {}", localTime8); // 출력: 10:08:20
    }

    @DisplayName("LocalTime 시간 관련 값들을 반환하는 메서드")
    @Test
    void localTime_method() {
        LocalTime localTime = LocalTime.of(13, 30, 20,1000);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        int nano = localTime.getNano();

        log.info("hour = {} ", hour);
        log.info("minute = {} ", minute);
        log.info("second = {} ", second);
        log.info("nano = {} ", nano);
    }

    @DisplayName("LocalDate 정적 팩토리 메서드")
    @Test
    void localDate_create_api() {

        // now 사용
        LocalDate localDate1 = LocalDate.now();
        log.info("localDate1 = {}", localDate1);

        // of 사용 1
        LocalDate localDate2 = LocalDate.of(2023, 8, 15);
        log.info("localDate2 = {}", localDate2);

        // of 사용 2
        LocalDate localDate3 = LocalDate.of(2023, Month.AUGUST, 15);
        log.info("localDate3 = {}", localDate3);

        // parse 사용 1
        LocalDate localDate4 = LocalDate.parse("2023-08-15");
        log.info("localDate4 = {}", localDate4);

        // parse 사용 2
        LocalDate localDate5 = LocalDate.parse("2023.08.15", DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        log.info("localDate5 = {}", localDate5);

        // from 사용 1
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate6 = LocalDate.from(localDateTime);
        log.info("localDate6 = {}", localDate6);

        // from 사용 2
        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                2023, 8, 15,
                8, 20, 30, 0,
                ZoneId.systemDefault());
        LocalDate localDate7 = LocalDate.from(zonedDateTime);  // 2023-08-15
        log.info("localDate7 = {}", localDate7);

        // ofInstant 사용 1
        Instant now = Instant.now();               // 현재 시간 기준 -9 시간을 뺀 LocalDate 생성
        ZoneId zoneId = ZoneId.systemDefault();    // "Asia/Seoul" 서울 시간대
        LocalDate localDate8 = LocalDate.ofInstant(now, zoneId); // 현재 서울의 날짜
        log.info("localDate8 = {}", localDate8);

        // ofInstant 사용 2
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");         // 서울 시간대
        ZoneId newYorkZone = ZoneId.of("America/New_York"); // 뉴욕 시간대
        LocalDate localDate9 = LocalDate.ofInstant(now, newYorkZone); // 특정 시간대의 날짜
        log.info("localDate9 = {}", localDate9);


        // ofEpochDay 사용
        LocalDate localDate10 = LocalDate.ofEpochDay(18628);
        log.info("localDate10 = {}", localDate10);

        // ofYearDay 사용
        LocalDate localDate11 = LocalDate.ofYearDay(2023, 227);
        log.info("localDate11 = {}", localDate11);

        LocalDate epoch = LocalDate.EPOCH;
        System.out.println("epoch = " + epoch);
        LocalDate max = LocalDate.MAX;
        LocalDate min = LocalDate.MIN;
        System.out.println("max = " + max);
        System.out.println("min = " + min);


    }
}
