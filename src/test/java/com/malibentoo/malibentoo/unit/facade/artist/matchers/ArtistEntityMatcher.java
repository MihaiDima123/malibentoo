package com.malibentoo.malibentoo.unit.facade.artist.matchers;

import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.malibentoo.unit.facade.artist.utils.TestArtistDTOFields;
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
}
