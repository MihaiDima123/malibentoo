package com.malibentoo.facade.artist.validator;

import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.core.validator.DtoValidator;
import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.exception.api.ApiExceptionFactory;
import com.malibentoo.facade.artist.counters.DefaultArtistCounter;
import com.malibentoo.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ArtistWriteValidator implements DtoValidator {
    private final DefaultArtistCounter defaultArtistCounter;

    public ArtistWriteValidator(DefaultArtistCounter defaultArtistCounter) {
        this.defaultArtistCounter = defaultArtistCounter;
    }

    @Override
    public void validateWrite(RestfulDTO restfulDTO) throws ApiException {
        if (restfulDTO instanceof ArtistDTO artistDTO) {
            validateArtistDtoWrite(artistDTO);
            return;
        }

        throw new IllegalArgumentException("RestfulDTO should be ArtistDTO");
    }

    public void validateArtistDtoWrite(ArtistDTO artistDTO) throws ApiException {
        if (StringUtils.isNullOrEmpty(artistDTO.getName())) {
            throw ApiExceptionFactory.propertyRequired(ValidationPropertyValues.NAME.getValue());
        }

        if (defaultArtistCounter.existsByName(artistDTO.getName())) {
            throw ApiExceptionFactory.propertyAlreadyExists(ValidationPropertyValues.NAME.getValue());
        }
    }
}
