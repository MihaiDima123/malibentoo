package com.malibentoo.malibentoo.unit.facade.artist.validator;

import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.core.restful.repo.EntityCounter;
import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.facade.artist.counters.DefaultArtistCounter;
import com.malibentoo.facade.artist.validator.ArtistWriteValidator;
import com.malibentoo.malibentoo.unit.facade.artist.utils.TestArtistEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistWriteValidatorTest {
    private final EntityCounter artistDtoCounterMock = mock(DefaultArtistCounter.class);
    private final ArtistWriteValidator artistWriteValidator = new ArtistWriteValidator(artistDtoCounterMock);
    private ArtistDTO artistDTO;

    private static class NotAnArtistInstance extends RestfulDTO { }

    @BeforeEach
    public void beforeEach() {
        artistDTO = TestArtistEntityFactory.newBaseArtistDto();
    }

    @Test
    public void testValidateWrite_givenCorrectInstanceType_shouldDoNothing() throws ApiException {
        when(artistDtoCounterMock.existsByName(artistDTO.getName())).thenReturn(false);

        artistWriteValidator.validateWrite(artistDTO);

        verify(artistDtoCounterMock, times(1)).existsByName(artistDTO.getName());
    }

    @Test
    public void testValidateWrite_givenIncorrectInstanceType_shouldThrowApiException() {
        assertThrows(IllegalArgumentException.class, () -> artistWriteValidator.validateWrite(new NotAnArtistInstance()));
        verify(artistDtoCounterMock, never()).existsByName(anyString());
    }

    @Test
    public void testValidateArtistDtoWrite_givenCorrectBody_shouldDoNothing() throws ApiException {
        when(artistDtoCounterMock.existsByName(artistDTO.getName())).thenReturn(false);

        artistWriteValidator.validateArtistDtoWrite(artistDTO);

        verify(artistDtoCounterMock, times(1)).existsByName(artistDTO.getName());
    }

    @Test
    public void testValidateArtistDtoWrite_givenNullName_shouldThrowApiException() {
        artistDTO.setName(null);
        assertThrows(ApiException.class, () -> artistWriteValidator.validateArtistDtoWrite(artistDTO));

        verify(artistDtoCounterMock, never()).existsByName(anyString());
    }

    @Test
    public void testValidateArtistDtoWrite_givenEmptyName_shouldThrowApiException() {
        artistDTO.setName("  ");
        assertThrows(ApiException.class, () -> artistWriteValidator.validateArtistDtoWrite(artistDTO));

        artistDTO.setName("");
        assertThrows(ApiException.class, () -> artistWriteValidator.validateArtistDtoWrite(artistDTO));

        verify(artistDtoCounterMock, never()).existsByName(anyString());
    }

    @Test
    public void testValidateArtistDtoWrite_givenExistingArtistName_shouldThrowApiException() {
        when(artistDtoCounterMock.existsByName(artistDTO.getName())).thenReturn(true);

        assertThrows(ApiException.class, () -> artistWriteValidator.validateArtistDtoWrite(artistDTO));

        verify(artistDtoCounterMock, times(1)).existsByName(artistDTO.getName());
    }
}
