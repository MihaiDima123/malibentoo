package com.malibentoo.core.restful;

import com.malibentoo.core.annotations.crud.ValidateEntityBefore;
import com.malibentoo.core.functional.ApiConsumer;
import com.malibentoo.core.validator.DtoValidator;
import com.malibentoo.exception.api.ApiException;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public abstract class BaseService<EntityType extends RestfulEntity> {
    protected abstract EntityType doCreate(EntityType obj);
    protected abstract EntityType doUpdate(EntityType obj);
    protected abstract EntityType doGetById(Integer id);

    private static final String METHOD_DO_CREATE = "doCreate";
    private static final String METHOD_DO_UPDATE = "doUpdate";

    public abstract void delete(EntityType obj);

    private final ApplicationContext applicationContext;

    public BaseService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public RestfulEntity create(RestfulDTO obj) throws ApiException {
        applyAnnotation(
                METHOD_DO_CREATE,
                ValidateEntityBefore.class,
                getValidateEntityBeforeApiConsumer(obj)
        );

        return doCreate(obj.toEntity());
    }

    public RestfulEntity update(RestfulDTO obj) throws ApiException {
        applyAnnotation(
                METHOD_DO_UPDATE,
                ValidateEntityBefore.class,
                getValidateEntityBeforeApiConsumer(obj)
        );
        return doUpdate(obj.toEntity());
    }

    public RestfulDTO getById(Integer id) {
        return doGetById(id).toDTO();
    }

    private ApiConsumer<ValidateEntityBefore> getValidateEntityBeforeApiConsumer(RestfulDTO obj) {
        return annotation -> {
            var validatorBean = applicationContext.getBean(annotation.value());
            if (validatorBean instanceof DtoValidator validateEntityBefore) {
                validateEntityBefore.validateWrite(obj);
            } else {
                throw new RuntimeException(validatorBean.getClass().getSimpleName() + " is not a validator");
            }
        };
    }

    private <AnnotationType extends Annotation> void applyAnnotation(String methodName,
                                                                     Class<AnnotationType> annotationClass,
                                                                     ApiConsumer<AnnotationType> consumer) throws ApiException {

        var annotation = Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(it -> it.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Method " + methodName + " not found"))
                .getAnnotation(annotationClass);

        if (annotation != null) {
            consumer.accept(annotation);
        }
    }
}
