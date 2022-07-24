package com.example.codesharingplatform.util;

import lombok.val;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private LocalDateTimeUtil() {
    }

    public static String convertToStringFormat(LocalDateTime date) {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(formatter);
    }

    public static LocalDateTime convertToLDTFormat(String date) {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(date, formatter);
    }

    public static long findDifferenceInSeconds(String firstDate, LocalDateTime secondDate) {
        val first = LocalDateTimeUtil.convertToLDTFormat(firstDate);
        return ChronoUnit.SECONDS.between(first, secondDate);
    }
}
