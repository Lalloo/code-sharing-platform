package com.example.codesharingplatform.domain;

import com.example.codesharingplatform.util.LocalDateTimeUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CODE_SNIPPETS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CodeSnippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    String code;

    @Column(name = "date")
    @JsonProperty("date")
    String createAt = LocalDateTimeUtil.convertToStringFormat(LocalDateTime.now());

    long time;

    long views;

    String token = UUID.randomUUID().toString();

    boolean restrictedByViews;

    boolean restrictedByTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CodeSnippet that = (CodeSnippet) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
