package com.malibentoo.malibentoo.unit.facade.artist.service;

import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.data.entities.Artist;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.facade.artist.counters.DefaultArtistCounter;
import com.malibentoo.facade.artist.repository.ArtistRepository;
import com.malibentoo.facade.artist.service.ArtistService;
import com.malibentoo.malibentoo.unit.facade.artist.matchers.ArtistEntityMatcher;
import com.malibentoo.malibentoo.unit.facade.artist.utils.TestArtistEntityFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;

public class ArtistServiceTest {
    private final static Integer TEST_ARTIST_ID = 123;
    private final static Artist testArtistEntity = TestArtistEntityFactory.newBaseArtist();
    private final ArtistRepository artistRepositoryMock = mock(ArtistRepository.class);
    private final DefaultArtistCounter artistCounterMock = mock(DefaultArtistCounter.class);

    @BeforeAll
    public static void beforeAll() {
        testArtistEntity.setId(TEST_ARTIST_ID);
    }

    private final ArtistService artistService = new ArtistService(
            mock(ApplicationContext.class),
            artistRepositoryMock,
            artistCounterMock
    );

    @Test
    public void testDoGetById_givenExistingId_returnTheArtist() throws ApiException {
        when(artistRepositoryMock.getById(TEST_ARTIST_ID)).thenReturn(Optional.of(testArtistEntity));

        var serviceResult = artistService.getById(TEST_ARTIST_ID);

        var expectedResult = ArtistDTO
                .transformer()
                .from(testArtistEntity);

        assertThat(serviceResult, ArtistEntityMatcher.matchArtistDtoFields(expectedResult));

        verify(artistRepositoryMock, times(1)).getById(TEST_ARTIST_ID);
    }

    @Test
    public void testDoGetById_givenNonExistentId_shouldThrowAnApiException() {
        when(artistRepositoryMock.getById(TEST_ARTIST_ID)).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> artistService.getById(TEST_ARTIST_ID));

        verify(artistRepositoryMock, times(1)).getById(TEST_ARTIST_ID);
    }

    @Test
    public void testDoCreate_givenDto_shouldReturnExpectedValueByCallingRepository() throws ApiException {
        when(artistRepositoryMock.save(any())).thenReturn(testArtistEntity);
        ArtistDTO dto = ArtistDTO.transformer().from(testArtistEntity);

        assertThat(dto, ArtistEntityMatcher.matchArtistDtoFields(artistService.create(dto)));

        verify(artistRepositoryMock, times(1)).save(any());
    }

    @Test
    public void testDoUpdate_givenDto_shouldAssertThatIdExists() {
        when(artistRepositoryMock.getById(TEST_ARTIST_ID)).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> artistService.update(ArtistDTO.transformer().from(testArtistEntity)));

        verify(artistRepositoryMock, times(1)).getById(TEST_ARTIST_ID);
        verify(artistRepositoryMock, never()).save(any());
    }

    @Test
    public void testDoUpdate_givenDto_shouldReturnTransformedRepositoryResponseAfterTransformingTheResult() throws ApiException {
        when(artistRepositoryMock.save(any())).thenReturn(testArtistEntity);
        when(artistRepositoryMock.getById(TEST_ARTIST_ID)).thenReturn(Optional.of(testArtistEntity));

        ArtistDTO dto = ArtistDTO.transformer().from(testArtistEntity);

        assertThat(dto, ArtistEntityMatcher.matchArtistDtoFields(
                artistService.update(dto)
        ));

        verify(artistRepositoryMock, times(1)).save(any());
        verify(artistRepositoryMock, times(1)).getById(TEST_ARTIST_ID);
    }

    @Test
    public void testDoDelete_givenExistingId_shouldCallRepositoryWithThatId() throws ApiException {
        when(artistCounterMock.existsById(TEST_ARTIST_ID)).thenReturn(true);

        artistService.delete(TEST_ARTIST_ID);

        verify(artistCounterMock, times(1)).existsById(TEST_ARTIST_ID);
        verify(artistRepositoryMock, times(1)).removeArtistById(TEST_ARTIST_ID);
    }

    @Test
    public void testDoDelete_givenNonExistentId_shouldThrowAnApiException() {
        when(artistCounterMock.existsById(TEST_ARTIST_ID)).thenReturn(false);

        assertThrows(ApiException.class, () -> artistService.delete(TEST_ARTIST_ID));

        verify(artistCounterMock, times(1)).existsById(TEST_ARTIST_ID);
        verify(artistRepositoryMock, never()).removeArtistById(TEST_ARTIST_ID);
    }
}
