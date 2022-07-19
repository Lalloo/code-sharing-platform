package com.example.codesharingplatform;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    String code = "" +
            "public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
            "}";
    @JsonProperty("date")
    String formatDateTime = "2020/01/01 12:00:03";
}
