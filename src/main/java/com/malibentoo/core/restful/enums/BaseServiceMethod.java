package com.malibentoo.core.restful.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseServiceMethod {
    DO_CREATE("doCreate"),
    DO_UPDATE("doUpdate"),
    DO_DELETE("doDelete");

    private final String method;
}
