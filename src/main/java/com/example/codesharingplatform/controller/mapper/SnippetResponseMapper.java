package com.example.codesharingplatform.controller.mapper;

import com.example.codesharingplatform.controller.dto.SnippetResponseDto;
import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.service.CodeFragmentService;
import com.example.codesharingplatform.util.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SnippetResponseMapper {

    CodeFragmentService codeFragmentService;

    public SnippetResponseDto toDto(CodeSnippet codeSnippet) {
        return SnippetResponseDto.builder()
                .code(codeSnippet.getCode())
                .date(codeSnippet.getCreateAt())
                .time(codeSnippet.getTime()) // получить время которое еще может жить снипет
                .views(codeSnippet.getViews())
                .build();
    }

    public List<SnippetResponseDto> allToDto(List<CodeSnippet> codeSnippets) {
        return codeSnippets.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
