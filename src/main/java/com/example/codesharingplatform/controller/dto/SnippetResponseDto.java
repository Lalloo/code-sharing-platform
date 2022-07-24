package com.example.codesharingplatform.controller.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SnippetResponseDto {
    String code;
    String date;
    long time;
    long views;
}
