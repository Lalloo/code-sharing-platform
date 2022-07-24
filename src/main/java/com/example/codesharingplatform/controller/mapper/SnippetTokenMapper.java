package com.example.codesharingplatform.controller.mapper;

import com.example.codesharingplatform.controller.dto.SnippetTokenDto;
import com.example.codesharingplatform.domain.CodeSnippet;
import org.springframework.stereotype.Component;

@Component
public class SnippetTokenMapper {

    public SnippetTokenDto toDto(CodeSnippet codeSnippet) {
        return SnippetTokenDto.builder()
                .token(codeSnippet.getToken())
                .build();
    }
}
