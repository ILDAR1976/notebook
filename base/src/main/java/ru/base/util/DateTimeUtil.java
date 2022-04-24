package ru.base.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_PATTERN_FOR_GETTER = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_PATTERN_FOR_SETTER = "yyyy-MM-dd'T'HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static final  LocalDate MIN_DATE = LocalDate.of(0,1,1);
    public static final  LocalDate MAX_DATE = LocalDate.of(3000,1,1);

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetween(LocalDateTime ldt, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ldt.compareTo(startDateTime) >= 0 && ldt.compareTo(endDateTime) <= 0;
    }

    private DateTimeUtil() {
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str, LocalDate def){
        return StringUtils.isEmpty(str) ? def :LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str, LocalTime def){
        return StringUtils.isEmpty(str) ? def :LocalTime.parse(str);
    }

    public static LocalDateTime getStartInclusive(LocalDate localDate) {
        return startOfDay(localDate != null ? localDate : MIN_DATE);
    }

    public static LocalDate parseLocalDate(String str){
        return StringUtils.isEmpty(str) ? LocalDateTime.now().toLocalDate() :LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str){
        return StringUtils.isEmpty(str) ? LocalDateTime.now().toLocalTime() :LocalTime.parse(str);
    }




    public static LocalDateTime getEndExclusive(LocalDate localDate) {
        return startOfDay(localDate != null ? localDate.plus(1, ChronoUnit.DAYS) : MAX_DATE);
    }

    private static LocalDateTime startOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
}

