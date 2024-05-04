package com.malibentoo.core.functional;

import com.malibentoo.exception.api.ApiException;

@FunctionalInterface
public interface ApiConsumer<T> {
    void accept(T t) throws ApiException;
}
