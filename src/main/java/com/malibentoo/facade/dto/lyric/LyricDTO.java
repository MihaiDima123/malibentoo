package com.malibentoo.facade.dto.lyric;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.facade.dto.song.SongDTO;
import com.malibentoo.facade.entities.Lyric;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LyricDTO {
    private Integer id;
    private String content;
    private SongDTO song;

    public static LyricDTO from(Lyric lyric) {
        return LyricDTO
                .builder()
                .id(lyric.getId())
                .content(lyric.getContent())
                .song(Optional
                        .ofNullable(lyric.getSong())
                        .map(SongDTO::from)
                        .orElse(null))
                .build();
    }
}
