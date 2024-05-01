package com.malibentoo.core.validator;

import com.malibentoo.exception.api.ApiException;

public interface Validator {
    void validate() throws ApiException;
}
