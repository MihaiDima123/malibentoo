package com.malibentoo.data.dto.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.core.restful.RestfulDTO;
import com.malibentoo.core.restful.RestfulEntity;
import com.malibentoo.data.entities.Artist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDTO extends RestfulDTO {
    private Integer id;
    private String name;

    public static ArtistDTO from(Artist artist) {
        return ArtistDTO
                .builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
    }

    @Override
    protected RestfulEntity doToEntity() {
        final var artist = new Artist();
        artist.setId(id);
        artist.setName(name);

        return artist;
    }
}
