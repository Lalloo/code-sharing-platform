package com.example.codesharingplatform.repository;

import domain.CodeSnippet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeSnippets {
    private final List<CodeSnippet> codeFragments = new ArrayList<>();

    public List<CodeSnippet> getAll() {
        return codeFragments;
    }

    public CodeSnippet add(CodeSnippet codeFragment) {
        codeFragment.setId(codeFragments.size() + 1);
        codeFragments.add(codeFragment);
        return codeFragment;
    }

    public CodeSnippet getById(int id) {
        return codeFragments.stream()
                .filter(cf -> cf.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<CodeSnippet> getLatest() {
        return codeFragments.stream()
                .sorted(Comparator.comparing(CodeSnippet::getId).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
