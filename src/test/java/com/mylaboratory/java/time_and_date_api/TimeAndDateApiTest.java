package com.mylaboratory.java.time_and_date_api;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

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

        // 그 외 클래스 변수
        LocalTime min = LocalTime.MIN;
        LocalTime max = LocalTime.MAX;
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalTime noon = LocalTime.NOON;
        log.info("min = {}", min);
        log.info("max = {}", max);
        log.info("midnight = {}", midnight);
        log.info("noon = {}", noon);
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
        LocalDate max = LocalDate.MAX;
        LocalDate min = LocalDate.MIN;
        log.info("epoch = {}", epoch);
        log.info("max = {}", max);
        log.info("min = {}", min);
    }

    @DisplayName("LocalDate 정적 팩토리 메서드")
    @Test
    void localDateTime_create_api() {

        // now 사용
        LocalDateTime localDateTime1 = LocalDateTime.now();
        log.info("localDateTime1 = {}", localDateTime1);

        // of 사용 1
        LocalDateTime localDateTime2 = LocalDateTime.of(2023, 8, 19, 13, 30);
        LocalDateTime localDateTime3 = LocalDateTime.of(2023, 8, 19, 13, 30, 11);
        LocalDateTime localDateTime4 = LocalDateTime.of(2023, 8, 19, 13, 30, 11, 1000);
        LocalDateTime localDateTime5 = LocalDateTime.of(2023, Month.AUGUST, 19, 13, 30);
        LocalDateTime localDateTime6 = LocalDateTime.of(2023, Month.AUGUST, 19, 13, 30, 12);
        LocalDateTime localDateTime7 = LocalDateTime.of(2023, Month.AUGUST, 19, 13, 30, 12, 2000);

        log.info("localDateTime2 = {}", localDateTime2);
        log.info("localDateTime3 = {}", localDateTime3);
        log.info("localDateTime4 = {}", localDateTime4);
        log.info("localDateTime5 = {}", localDateTime5);
        log.info("localDateTime6 = {}", localDateTime6);
        log.info("localDateTime7 = {}", localDateTime7);

        // of 사용 2
        LocalDateTime localDateTime10 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        log.info("localDateTime10 = {}", localDateTime10);


        // parse 사용 1
        LocalDateTime localDateTime11 = LocalDateTime.parse("2023-08-19T14:03:30");
        log.info("localDateTime11 = {}", localDateTime11);

        // parse 사용 2
        LocalDateTime localDateTime12 = LocalDateTime.parse("2023.08.19 14:03:30", DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        log.info("localDateTIme12 = {}", localDateTime12);


        // from 사용 1
        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                2023, 8, 15,
                8, 20, 30, 0,
                ZoneId.systemDefault());
        LocalDateTime localDateTime13 = LocalDateTime.from(zonedDateTime);
        log.info("localDateTime13 = {}", localDateTime13);

        // ofInstant 사용 1
        Instant now = Instant.now();               // 현재 시간 기준 -9 시간을 뺀 LocalDate 생성
        ZoneId zoneId = ZoneId.systemDefault();    // "Asia/Seoul" 서울 시간대
        LocalDateTime localDateTime14 = LocalDateTime.ofInstant(now, zoneId); // 현재 서울의 날짜와 시간
        log.info("localDateTime14 = {}", localDateTime14);

        // ofInstant 사용 2
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");         // 서울 시간대
        ZoneId newYorkZone = ZoneId.of("America/New_York"); // 뉴욕 시간대
        LocalDateTime localDateTime15 = LocalDateTime.ofInstant(now, newYorkZone); // 뉴욕 날짜와 시간
        log.info("localDateTime15 = {}", localDateTime15);

        // 그외 클래스 변수
        LocalDateTime min = LocalDateTime.MIN;
        LocalDateTime max = LocalDateTime.MAX;
        log.info("min = {}", min);
        log.info("max = {}", max);

        // with 사용 1
        LocalDateTime oldLocalDateTime = LocalDateTime.of(2023, 8, 19, 15, 3, 22, 1000);
        LocalDateTime newLocalDateTime1 = oldLocalDateTime.with(ChronoField.HOUR_OF_DAY, 16);
        LocalDateTime newLocalDateTime2 = oldLocalDateTime.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime newLocalDateTime3 = oldLocalDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        log.info("oldLocalDateTime = {}", oldLocalDateTime);
        log.info("newLocalDateTime1 = {}", newLocalDateTime1);
        log.info("newLocalDateTime2 = {}", newLocalDateTime2);
        log.info("newLocalDateTime3 = {}", newLocalDateTime3);

        // with 관련 메서드 사용 1
        LocalDateTime newLocalDateTime4 = oldLocalDateTime.withYear(2022);
        LocalDateTime newLocalDateTime5 = oldLocalDateTime.withMonth(9);
        LocalDateTime newLocalDateTime6 = oldLocalDateTime.withDayOfMonth(22);
        LocalDateTime newLocalDateTime7 = oldLocalDateTime.withHour(20);
        LocalDateTime newLocalDateTime8 = oldLocalDateTime.withMinute(30);
        LocalDateTime newLocalDateTime9 = oldLocalDateTime.withSecond(45);
        LocalDateTime newLocalDateTime10 = oldLocalDateTime.withNano(1000);

        log.info("newLocalDateTime4 = {}", newLocalDateTime4);
        log.info("newLocalDateTime5 = {}", newLocalDateTime5);
        log.info("newLocalDateTime6 = {}", newLocalDateTime6);
        log.info("newLocalDateTime7 = {}", newLocalDateTime7);
        log.info("newLocalDateTime8 = {}", newLocalDateTime8);
        log.info("newLocalDateTime9 = {}", newLocalDateTime9);
        log.info("newLocalDateTime10 = {}", newLocalDateTime10);


        // truncatedTo 사용 1
        LocalDateTime localDateTime16 = oldLocalDateTime.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime localDateTime17 = oldLocalDateTime.truncatedTo(ChronoUnit.HOURS);
        LocalDateTime localDateTime18 = oldLocalDateTime.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime localDateTime19 = oldLocalDateTime.truncatedTo(ChronoUnit.SECONDS);

        log.info("localDateTime16 = {}", localDateTime16);
        log.info("localDateTime17 = {}", localDateTime17);
        log.info("localDateTime18 = {}", localDateTime18);
        log.info("localDateTime19 = {}", localDateTime19);



    }
}
