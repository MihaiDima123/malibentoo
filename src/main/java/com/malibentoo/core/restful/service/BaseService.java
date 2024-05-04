package com.malibentoo.core.restful.service;

import com.malibentoo.core.annotations.crud.AnnotationProvider;
import com.malibentoo.core.annotations.crud.ValidateEntityBefore;
import com.malibentoo.core.restful.enums.BaseServiceMethod;
import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.core.restful.objects.RestfulEntity;
import com.malibentoo.core.validator.DtoValidator;
import com.malibentoo.exception.api.ApiException;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

public abstract class BaseService<EntityType extends RestfulEntity> implements AnnotationProvider {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationContext applicationContext;

    private DtoValidator createValidator;
    private DtoValidator updateValidator;
    private DtoValidator deleteValidator;

    public BaseService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // Abstracts
    protected abstract EntityType doCreate(EntityType obj);
    protected abstract EntityType doUpdate(EntityType obj);
    protected abstract EntityType doGetById(Integer id);
    protected abstract Integer doDelete(EntityType obj);

    // Proxy
    @SuppressWarnings("unused")
    public RestfulEntity create(RestfulDTO obj) throws ApiException {
        if (createValidator != null) {
            createValidator.validateWrite(obj);
        }
        return doCreate(obj.toEntity());
    }

    @SuppressWarnings("unused")
    public RestfulEntity update(RestfulDTO obj) throws ApiException {
        if (updateValidator != null) {
            updateValidator.validateWrite(obj);
        }
        return doUpdate(obj.toEntity());
    }

    @SuppressWarnings("unused")
    public Integer delete(RestfulDTO obj) throws ApiException {
        if (deleteValidator != null)  {
            deleteValidator.validateWrite(obj);
        }
        return doDelete(obj.toEntity());
    }

    @SuppressWarnings("unused")
    public RestfulDTO getById(Integer id) {
        return doGetById(id).toDTO();
    }

    // Utils
    @Nullable
    private DtoValidator getValidatorBeanForAnnotation(@Nonnull Annotation annotation) {
        if (annotation instanceof ValidateEntityBefore validateEntityBeforeAnnotation) {
            return applicationContext.getBean(validateEntityBeforeAnnotation.value(), DtoValidator.class);
        }

        return null;
    }

    private void applyValidator(BaseServiceMethod method, Consumer<ValidateEntityBefore> consumer) {
        getAnnotationForMethod(method.getMethod(), ValidateEntityBefore.class).ifPresent(consumer);
    }

    @PostConstruct
    private void postConstruct() {
        applyValidator(BaseServiceMethod.DO_CREATE, validateEntityBefore ->
                createValidator = getValidatorBeanForAnnotation(validateEntityBefore));

        applyValidator(BaseServiceMethod.DO_UPDATE, validateEntityBefore ->
                updateValidator = getValidatorBeanForAnnotation(validateEntityBefore));

        applyValidator(BaseServiceMethod.DO_DELETE, validateEntityBefore ->
                deleteValidator = getValidatorBeanForAnnotation(validateEntityBefore));
    }
}
