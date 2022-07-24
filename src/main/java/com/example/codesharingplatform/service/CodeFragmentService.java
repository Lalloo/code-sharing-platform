package com.example.codesharingplatform.service;

import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.repository.CodeSnippetRepository;
import com.example.codesharingplatform.util.LocalDateTimeUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CodeFragmentService {
    CodeSnippetRepository repo;

    public CodeSnippet findSnippetByToken(String token) {
        return repo.findByToken(token).orElseThrow();
    }

    public CodeSnippet save(CodeSnippet toSave) {
        return repo.save(toSave);
    }

    public List<CodeSnippet> findLatest10() {
        return repo.findTop10ByOrderByIdDesc();
    }

    public Optional<CodeSnippet> updateTimeAndViews(CodeSnippet codeSnippet) {
        if (codeSnippet.isRestrictedByTime()) {
            Optional<CodeSnippet> update = updateTimeOrDelete(codeSnippet);
            if (update.isPresent() && codeSnippet.isRestrictedByViews()) {
                return updateViewsOrDelete(codeSnippet);
            }
            return update;
        } else {
            return Optional.of(codeSnippet);
        }
    }

    private Optional<CodeSnippet> updateTimeOrDelete(CodeSnippet codeSnippet) {
        long time = codeSnippet.getTime() - LocalDateTimeUtil.convertToLDTFormat(codeSnippet.getCreateAt()).getSecond();
        if (time < 0) {
            repo.delete(codeSnippet);
            return Optional.empty();
        }
        codeSnippet.setTime(time);
        return Optional.of(repo.save(codeSnippet));
    }

    private Optional<CodeSnippet> updateViewsOrDelete(CodeSnippet codeSnippet) {
        long views = codeSnippet.getViews() - 1;
        if (views < 0) {
            repo.delete(codeSnippet);
            return Optional.empty();
        }
        codeSnippet.setViews(views);
        return Optional.of(repo.save(codeSnippet));
    }
}
