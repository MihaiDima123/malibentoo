package com.malibentoo.facade.dto.song;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.facade.dto.artist.ArtistDTO;
import com.malibentoo.facade.dto.genre.GenreDTO;
import com.malibentoo.facade.dto.producer.ProducerDTO;
import com.malibentoo.facade.entities.Song;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongDTO {
    private Integer id;
    private String name;
    private ProducerDTO producer;
    private List<GenreDTO> genres;
    private List<ArtistDTO> performers;

    public static SongDTO from(Song song) {
        return SongDTO
                .builder()
                .id(song.getId())
                .name(song.getName())
                .producer(Optional
                        .ofNullable(song.getProducer())
                        .map(ProducerDTO::from)
                        .orElse(null)
                )
                .genres(Optional
                        .ofNullable(song.getSongGenres())
                        .map(genres -> genres.stream().map(GenreDTO::from).toList())
                        .orElse(null)
                )
                .performers(Optional
                        .ofNullable(song.getSongPerformers())
                        .map(artists -> artists.stream().map(ArtistDTO::from).toList())
                        .orElse(null)
                )
                .build();
    }
}
