package com.example.codesharingplatform.repository;

import com.example.codesharingplatform.domain.CodeSnippet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, Long> {

    @Query("select c from CodeSnippet c where c.restrictedByTime = false and c.restrictedByViews = false order by c.id desc")
    List<CodeSnippet> findTop10ByOrderByIdDesc(Pageable pageable);

    Optional<CodeSnippet> findByToken(String token);
}
