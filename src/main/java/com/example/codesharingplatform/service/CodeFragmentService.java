package com.example.codesharingplatform.service;

import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.repository.CodeSnippetRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CodeFragmentService {
    CodeSnippetRepository repo;

    public CodeSnippet findSnippetById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public CodeSnippet save(CodeSnippet toSave) {
        return repo.save(toSave);
    }

    public List<CodeSnippet> findLatest10() {
        return repo.findTop10ByOrderByIdDesc();
    }
}
