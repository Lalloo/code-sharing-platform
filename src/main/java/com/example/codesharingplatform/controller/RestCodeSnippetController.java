package com.example.codesharingplatform.controller;

import com.example.codesharingplatform.controller.dto.SnippetRequestDto;
import com.example.codesharingplatform.controller.dto.SnippetResponseDto;
import com.example.codesharingplatform.controller.dto.SnippetTokenDto;
import com.example.codesharingplatform.controller.mapper.SnippetRequestMapper;
import com.example.codesharingplatform.controller.mapper.SnippetResponseMapper;
import com.example.codesharingplatform.controller.mapper.SnippetTokenMapper;
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
        val snippet = codeFragmentService.findSnippetByToken(token);
        return ResponseEntity.ok(snippetResponseMapper.toDto(snippet));
    }

    @PostMapping("/new")
    public ResponseEntity<SnippetTokenDto> postApiCodeNew(@RequestBody SnippetRequestDto dto) {
        val save = codeFragmentService.save(snippetRequestMapper.toModel(dto));
        return ResponseEntity.ok(snippetTokenMapper.toDto(save));
    }
}
