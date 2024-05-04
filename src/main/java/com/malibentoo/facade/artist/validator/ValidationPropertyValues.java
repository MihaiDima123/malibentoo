package com.malibentoo.facade.artist.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationPropertyValues {
    NAME("Name");
    private final String value;
}
