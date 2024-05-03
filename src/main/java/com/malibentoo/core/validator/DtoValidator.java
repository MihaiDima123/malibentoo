package com.malibentoo.core.validator;

import com.malibentoo.core.restful.RestfulDTO;
import com.malibentoo.exception.api.ApiException;

public interface DtoValidator {
    void validateWrite(RestfulDTO restfulDTO) throws ApiException;
}
