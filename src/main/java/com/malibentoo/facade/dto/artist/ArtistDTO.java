package com.malibentoo.facade.dto.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.facade.entities.Artist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDTO {
    private Integer id;
    private String name;

    public static ArtistDTO from(Artist artist) {
        return ArtistDTO
                .builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
    }
}
