package com.malibentoo.facade.artist.transformers;

import com.malibentoo.core.restful.transformer.DtoTransformer;
import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.data.entities.Artist;

public class ArtistDtoTransformer implements DtoTransformer<ArtistDTO, Artist> {
    @Override
    public ArtistDTO from(Artist artist) {
        return ArtistDTO
                .builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
    }

    @Override
    public Artist to(ArtistDTO artistDTO) {
        return Artist
                .builder()
                .id(artistDTO.getId())
                .name(artistDTO.getName())
                .build();
    }
}
