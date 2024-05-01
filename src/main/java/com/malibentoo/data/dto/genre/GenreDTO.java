package com.malibentoo.data.dto.genre;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.data.entities.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDTO {
    private Integer id;
    private String name;

    public static GenreDTO from(Genre genre) {
        return GenreDTO
                .builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
