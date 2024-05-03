package com.malibentoo.facade.artist.validator;

import com.malibentoo.core.restful.RestfulDTO;
import com.malibentoo.core.validator.DtoValidator;
import com.malibentoo.exception.api.ApiException;
import org.springframework.stereotype.Component;

@Component
public class ArtistWriteValidator implements DtoValidator {
    @Override
    public void validateWrite(RestfulDTO restfulDTO) throws ApiException {
        System.out.println("HAHA");
    }
}
