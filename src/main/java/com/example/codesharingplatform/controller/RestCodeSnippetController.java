package com.example.codesharingplatform.controller;

import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.service.CodeFragmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/code")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class RestCodeSnippetController {

    CodeFragmentService codeFragmentService;

    @GetMapping("/latest")
    public List<CodeSnippet> returnLatestApiFragments() {
        return codeFragmentService.findLatest10();
    }

    @GetMapping("/{id}")
    public CodeSnippet getFragment(@PathVariable Long id) {
        return codeFragmentService.findSnippetById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> postApiCodeNew(@RequestBody CodeSnippet codeFragment) {
        var saved = codeFragmentService.save(new CodeSnippet(codeFragment.getCode()));
        return ResponseEntity.ok("{ \"id\" : \"" + saved.getId() + "\" }");
    }
}
