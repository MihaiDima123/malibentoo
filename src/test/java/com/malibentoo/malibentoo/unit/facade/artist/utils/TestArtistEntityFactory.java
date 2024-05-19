package com.malibentoo.malibentoo.unit.facade.artist.utils;

import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.data.entities.Artist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestArtistEntityFactory {
    public static DateTimeFormatter TEST_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static String ARTIST_NAME = "John Doe";
    private final static Integer ARTIST_ID = 123;
    private final static LocalDateTime ARTIST_DELETED_AT_DATE = LocalDateTime.parse("1986-04-08 12:30", TEST_DATE_FORMATTER);

    public static Artist newBaseArtist() {
        final var artist = new Artist();
        artist.setName(ARTIST_NAME);

        return artist;
    }

    public static ArtistDTO newBaseArtistDto() {
        return ArtistDTO.builder()
                .id(ARTIST_ID)
                .name(ARTIST_NAME)
                .build();
    }

}
