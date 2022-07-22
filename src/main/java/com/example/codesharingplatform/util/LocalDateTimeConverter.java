package com.example.codesharingplatform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private LocalDateTimeConverter() { }

    public static String convert(LocalDateTime date) {
        var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(formatter);
    }
}
