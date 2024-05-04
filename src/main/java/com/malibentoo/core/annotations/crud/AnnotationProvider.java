package com.malibentoo.core.annotations.crud;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

public interface AnnotationProvider {
    default <AnnotationType extends Annotation> Optional<AnnotationType> getAnnotationForMethod(String methodName,
                                                                                                Class<AnnotationType> annotationClass) {
        return Optional.ofNullable(
                Arrays.stream(this.getClass().getDeclaredMethods())
                        .filter(it -> it.getName().equals(methodName))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Method " + methodName + " not found"))
                        .getAnnotation(annotationClass)
        );
    }
}
