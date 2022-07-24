package com.example.codesharingplatform.service;

import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.exception.CodeNotFoundException;
import com.example.codesharingplatform.repository.CodeSnippetRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CodeFragmentService {
    CodeSnippetRepository repo;

    public CodeSnippet findSnippetByToken(String token) {
        val snippet = repo.findByToken(token)
                .orElseThrow();

        if (snippet.isRestrictedByTime() && snippet.isExpired()) {
            repo.delete(snippet);
            throw new CodeNotFoundException();
        }
        if (snippet.isRestrictedByViews()) {
            long views = snippet.getViews() - 1;
            if (views < 0) {
                repo.delete(snippet);
                throw new CodeNotFoundException();
            }
            snippet.setViews(views);
        }
        return repo.save(snippet);
    }

    public CodeSnippet save(CodeSnippet toSave) {
        return repo.save(toSave);
    }

    public List<CodeSnippet> findLatest10() {
        return repo.findTop10ByOrderByIdDesc(PageRequest.of(0, 10));
    }
}
