package com.example.codesharingplatform.controller;

import com.example.codesharingplatform.controller.dto.SnippetRequestDto;
import com.example.codesharingplatform.controller.dto.SnippetResponseDto;
import com.example.codesharingplatform.controller.dto.SnippetTokenDto;
import com.example.codesharingplatform.controller.mapper.SnippetRequestMapper;
import com.example.codesharingplatform.controller.mapper.SnippetResponseMapper;
import com.example.codesharingplatform.controller.mapper.SnippetTokenMapper;
import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.exception.CodeNotFoundException;
import com.example.codesharingplatform.service.CodeFragmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/code")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class RestCodeSnippetController {

    CodeFragmentService codeFragmentService;
    SnippetRequestMapper snippetRequestMapper;
    SnippetTokenMapper snippetTokenMapper;
    SnippetResponseMapper snippetResponseMapper;

    @GetMapping("/latest")
    public List<SnippetResponseDto> returnLatestApiFragments() {
        return snippetResponseMapper.allToDto(codeFragmentService.findLatest10());
    }

    @GetMapping("/{token}")
    public ResponseEntity<SnippetResponseDto> getFragment(@PathVariable String token) {
        var snippetByToken = codeFragmentService.findSnippetByToken(token);
        snippetByToken = codeFragmentService.updateTimeAndViews(snippetByToken).orElseThrow(CodeNotFoundException::new);
        return ResponseEntity.ok(snippetResponseMapper.toDto(snippetByToken));
    }

    @PostMapping("/new")
    public ResponseEntity<SnippetTokenDto> postApiCodeNew(@RequestBody SnippetRequestDto codeSnippetRequestDto) {
        val save = codeFragmentService.save(snippetRequestMapper.toCodeSnippet(codeSnippetRequestDto));
        return ResponseEntity.ok(snippetTokenMapper.toDto(save));
    }
}
