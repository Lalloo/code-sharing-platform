package com.example.codesharingplatform.controller;

import domain.CodeSnippet;
import com.example.codesharingplatform.repository.CodeSnippets;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/code")
@RestController
public class RestCodeSnippetController {

    private final CodeSnippets codeFragments;

    public RestCodeSnippetController(CodeSnippets codeFragments) {
        this.codeFragments = codeFragments;
    }

    @GetMapping("/latest")
    public List<CodeSnippet> returnLatestApiFragments() {
        return codeFragments.getLatest();
    }

    @GetMapping("/{id}")
    public CodeSnippet getFragment(@PathVariable int id) {
        return codeFragments.getById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> postApiCodeNew(@RequestBody CodeSnippet codeFragment) {
        CodeSnippet saved = codeFragments.add(new CodeSnippet(codeFragment.getCode()));
        return ResponseEntity.ok("{ \"id\" : " + saved.getId()  + " }");
    }
}
