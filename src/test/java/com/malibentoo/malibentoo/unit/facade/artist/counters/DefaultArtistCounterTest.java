package com.malibentoo.malibentoo.unit.facade.artist.counters;

import com.malibentoo.facade.artist.counters.DefaultArtistCounter;
import com.malibentoo.facade.artist.repository.ArtistRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;

public class DefaultArtistCounterTest {
    public static final int WITHOUT_INACTIVE_COUNT = 1;
    public static final int WITH_INACTIVE_COUNT = 2;
    private final static String TEST_ARTIST_NAME = "Artist name Test Unique";
    private final static Integer TEST_ARTIST_ID = 121;

    private final ArtistRepository artistRepository = mock(ArtistRepository.class);
    private final DefaultArtistCounter defaultArtistCounter = new DefaultArtistCounter(artistRepository);

    @Test
    public void testCountByNameIncludingInactiveCanCount() {
        expectToReturnCounts(WITH_INACTIVE_COUNT, WITHOUT_INACTIVE_COUNT);

        final var actualValue = defaultArtistCounter.countByName(TEST_ARTIST_NAME, true);

        verify(artistRepository, times(1)).getCountByNameIncludingInactive(TEST_ARTIST_NAME);
        verify(artistRepository, never()).getCountByName(anyString());

        assertEquals(actualValue, WITH_INACTIVE_COUNT);
    }

    @Test
    public void testCountByNameNotIncludingInactiveCanCount() {
        expectToReturnCounts(WITH_INACTIVE_COUNT, WITHOUT_INACTIVE_COUNT);

        final var actualValue = defaultArtistCounter.countByName(TEST_ARTIST_NAME, false);

        verify(artistRepository, times(1)).getCountByName(TEST_ARTIST_NAME);
        verify(artistRepository, never()).getCountByNameIncludingInactive(anyString());

        assertEquals(actualValue, WITHOUT_INACTIVE_COUNT);
    }

    @Test
    public void testCountByNameThrowsExceptionIfNameIsNull() {
        assertThrows(NullPointerException.class, () -> defaultArtistCounter.countByName(null, false));
        assertThrows(NullPointerException.class, () -> defaultArtistCounter.countByName(null, true));

        verify(artistRepository, never()).getCountByNameIncludingInactive(anyString());
        verify(artistRepository, never()).getCountByName(anyString());
    }

    @Test
    public void testExistsByNameIncludingInactiveWhenCountGreaterThanZeroReturnsTrue() {
        when(artistRepository.getCountByNameIncludingInactive(TEST_ARTIST_NAME)).thenReturn(WITH_INACTIVE_COUNT);
        assertTrue(defaultArtistCounter.existsByName(TEST_ARTIST_NAME, true));

        verify(artistRepository, times(1)).getCountByNameIncludingInactive(TEST_ARTIST_NAME);
        verify(artistRepository, never()).getCountByName(anyString());
    }

    @Test
    public void testExistsByNameIncludingInactiveWhenCountLessThanZeroReturnFalse() {
        when(artistRepository.getCountByNameIncludingInactive(TEST_ARTIST_NAME)).thenReturn(0);

        assertFalse(defaultArtistCounter.existsByName(TEST_ARTIST_NAME, true));

        verify(artistRepository, times(1)).getCountByNameIncludingInactive(TEST_ARTIST_NAME);
        verify(artistRepository, never()).getCountByName(anyString());
    }

    @Test
    public void testExistsByNameNotIncludingInactiveWhenCountGreaterThanZeroReturnsTrue() {
        when(artistRepository.getCountByName(TEST_ARTIST_NAME)).thenReturn(WITHOUT_INACTIVE_COUNT);

        assertTrue(defaultArtistCounter.existsByName(TEST_ARTIST_NAME, false));

        verify(artistRepository, never()).getCountByNameIncludingInactive(anyString());
        verify(artistRepository, times(1)).getCountByName(TEST_ARTIST_NAME);
    }

    @Test
    public void testExistsByNameNotIncludingInactiveWhenCountLessThanZeroReturnsFalse() {
        when(artistRepository.getCountByName(TEST_ARTIST_NAME)).thenReturn(0);

        assertFalse(defaultArtistCounter.existsByName(TEST_ARTIST_NAME, false));

        verify(artistRepository, never()).getCountByNameIncludingInactive(anyString());
        verify(artistRepository, times(1)).getCountByName(TEST_ARTIST_NAME);
    }

    @Test
    public void testExistsById_ifCountGreaterThanZeroIncludingInactive_shouldReturnTrue() {
        when(artistRepository.getCountByIdIncludingInactive(TEST_ARTIST_ID)).thenReturn(1);
        assertTrue(defaultArtistCounter.existsById(TEST_ARTIST_ID, true));

        verify(artistRepository, times(1)).getCountByIdIncludingInactive(TEST_ARTIST_ID);
        verify(artistRepository, never()).getCountById(TEST_ARTIST_ID);
    }

    @Test
    public void testExistsById_ifCountLessThanZeroIncludingInactive_shouldReturnFalse() {
        when(artistRepository.getCountByIdIncludingInactive(TEST_ARTIST_ID)).thenReturn(0);
        assertFalse(defaultArtistCounter.existsById(TEST_ARTIST_ID, true));

        verify(artistRepository, times(1)).getCountByIdIncludingInactive(TEST_ARTIST_ID);
        verify(artistRepository, never()).getCountById(anyInt());
    }

    @Test
    public void testExistsById_ifCountGreaterThanZeroNotIncludingInactive_shouldReturnTrue() {
        when(artistRepository.getCountById(TEST_ARTIST_ID)).thenReturn(1);
        assertTrue(defaultArtistCounter.existsById(TEST_ARTIST_ID, false));

        verify(artistRepository, never()).getCountByIdIncludingInactive(anyInt());
        verify(artistRepository, times(1)).getCountById(TEST_ARTIST_ID);
    }

    @Test
    public void testExistsById_ifCountLessThanZeroNotIncludingInactive_shouldReturnFalse() {
        when(artistRepository.getCountById(TEST_ARTIST_ID)).thenReturn(0);
        assertFalse(defaultArtistCounter.existsById(TEST_ARTIST_ID, false));

        verify(artistRepository, never()).getCountByIdIncludingInactive(anyInt());
        verify(artistRepository, times(1)).getCountById(TEST_ARTIST_ID);
    }

    private void expectToReturnCounts(int withInactiveCount, int withoutInactiveCount) {
        when(artistRepository.getCountByNameIncludingInactive(TEST_ARTIST_NAME))
                .thenReturn(withInactiveCount);

        when(artistRepository.getCountByName(TEST_ARTIST_NAME))
                .thenReturn(withoutInactiveCount);
    }
}
