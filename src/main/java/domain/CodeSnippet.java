package domain;

import com.example.codesharingplatform.util.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeSnippet {

    @JsonIgnore
    private int id;

    private String code;

    @JsonProperty("date")
    private String createAt = LocalDateTimeConverter.convert(LocalDateTime.now());

    public CodeSnippet(String code) {
        this.code = code;
    }
}
