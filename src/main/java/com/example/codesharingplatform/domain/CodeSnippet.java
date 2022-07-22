package com.example.codesharingplatform.domain;

import com.example.codesharingplatform.util.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "CODE_SNIPPETS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CodeSnippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @Column(name = "code")
    String code;

    @Column(name = "date")
    @JsonProperty("date")
    LocalDateTime createAt = LocalDateTime.now();

    public CodeSnippet(String code) {
        this.code = code;
    }
}
