package com.example.codesharingplatform.controller.mapper;

import com.example.codesharingplatform.controller.dto.SnippetRequestDto;
import com.example.codesharingplatform.domain.CodeSnippet;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class SnippetRequestMapper {

    public CodeSnippet toCodeSnippet(SnippetRequestDto snippetRequestDto) {
        val codeSnippet = new CodeSnippet();
        codeSnippet.setCode(snippetRequestDto.getCode());
        codeSnippet.setTime(snippetRequestDto.getTime());
        codeSnippet.setViews(snippetRequestDto.getViews());
        codeSnippet.setRestrictedByTime(codeSnippet.getTime() != 0);
        codeSnippet.setRestrictedByViews(codeSnippet.getViews() != 0);
        return codeSnippet;
    }
}
