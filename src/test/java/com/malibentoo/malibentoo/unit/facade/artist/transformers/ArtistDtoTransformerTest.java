package com.malibentoo.malibentoo.unit.facade.artist.transformers;

import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.data.entities.Artist;
import com.malibentoo.facade.artist.transformers.ArtistDtoTransformer;
import com.malibentoo.malibentoo.unit.facade.artist.matchers.ArtistEntityMatcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ArtistDtoTransformerTest {
    private static final Integer TEST_ID = 1;
    private static final String TEST_NAME = "name";

    private static final ArtistDtoTransformer transformer = new ArtistDtoTransformer();

    @Test
    public void testFromShouldBuildTheArtistDtoFromEntity() {
        final var artist = new Artist();
        artist.setId(TEST_ID);
        artist.setName(TEST_NAME);

        var result = transformer.from(artist);
        var expectedResult = ArtistDTO
                .builder()
                .id(TEST_ID)
                .name(TEST_NAME)
                .build();

        assertThat(result, ArtistEntityMatcher.matchArtistDtoFields(expectedResult));
    }

    @Test
    public void testFromShouldBuildTheArtistFromDto() {
        var dto = ArtistDTO
                .builder()
                .id(TEST_ID)
                .name(TEST_NAME)
                .build();

        var result = transformer.toBase(dto);
        var expectedResult = new Artist();
        expectedResult.setId(TEST_ID);
        expectedResult.setName(TEST_NAME);

        assertThat(result, ArtistEntityMatcher.matchArtistFields(expectedResult));
    }
}
