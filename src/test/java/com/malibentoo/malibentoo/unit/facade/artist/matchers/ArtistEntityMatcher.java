package com.malibentoo.malibentoo.unit.facade.artist.matchers;

import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.data.entities.Artist;
import com.malibentoo.malibentoo.unit.facade.artist.utils.TestArtistDTOFields;
import com.malibentoo.malibentoo.unit.facade.artist.utils.TestArtistFields;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalTo;

public class ArtistEntityMatcher {
    public static Matcher<ArtistDTO> matchArtistDtoFields(ArtistDTO artistDTO) {
        return allOf(
                hasProperty(TestArtistDTOFields.ID.value, equalTo(artistDTO.getId())),
                hasProperty(TestArtistDTOFields.NAME.value, equalTo(artistDTO.getName()))
        );
    }

    public static Matcher<Artist> matchArtistFields(Artist artist) {
        return allOf(
                hasProperty(TestArtistFields.ID.value, equalTo(artist.getId())),
                hasProperty(TestArtistFields.NAME.value, equalTo(artist.getName()))
        );
    }
}
