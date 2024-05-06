package com.malibentoo.malibentoo.unit.restful.helpers.baseservice;

import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.core.validator.DtoValidator;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.utils.StringUtils;

/**
 * Test: Validates a field is empty using string utils - tested l8r (hopefully)
 *
 */
public class BaseTestValidator implements DtoValidator {
    public static final String BASE_VALIDATOR_EMPTY_ERROR_MESSAGE =  "Field is required";
    public static final String BASE_VALIDATOR_INVALID_ERROR_MESSAGE =  "Not an instance of base dto";

    @Override
    public void validateWrite(RestfulDTO restfulDTO) throws ApiException {
        if (restfulDTO instanceof BaseTestDto dtoValidator) {
            if (StringUtils.isNullOrEmpty(dtoValidator.getField())) {
                throw new ApiException(BASE_VALIDATOR_EMPTY_ERROR_MESSAGE);
            }
            return;
        }

        throw new RuntimeException(BASE_VALIDATOR_INVALID_ERROR_MESSAGE);
    }
}
