package com.example.codesharingplatform.controller;

import com.example.codesharingplatform.Request;
import com.example.codesharingplatform.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class RequestController {
    private final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private final Response response = new Response();

    @GetMapping("/api/code")
    public Response getCode() {
        return response;
    }

    @GetMapping("/code")
    public String htmlPage() {
        return String.format("<html><head><title>Code</title></head><body><pre id=\"code_snippet\">%s</pre><p><span id=\"load_date\">%s</span></p></body></html>", response.getCode(), response.getFormatDateTime());
    }

    @GetMapping("/code/new")
    public String postCode() {
        return "<html><head><title>Create</title></head><body><textarea id=\"code_snippet\">write something</textarea><button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button></body>";
    }

    @PostMapping("api/code/new")
    public String returnEmptyJson(@org.springframework.web.bind.annotation.RequestBody Request code) {
        response.setCode(code.getCode());
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = localDateTime.format(formatter);
        response.setFormatDateTime(formatDateTime);
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        return "{}";
    }
}
