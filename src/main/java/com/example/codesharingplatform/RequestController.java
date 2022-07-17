package com.example.codesharingplatform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {


    private static String code = "" +
            "public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
            "}";

    private static String html = "<html>\n" +
            "<head>\n" +
            "    <title>Code</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <pre>\n" +
            code + "\n" +
            "    </pre>\n" +
            "</body>\n" +
            "</html>";

    @GetMapping("/api/code")
    public Response getCode() {
        Response response = new Response();
        response.setCode(code);
        return response;
    }

    @GetMapping("/code")
    public String getCodes() {
        return html;
    }
}