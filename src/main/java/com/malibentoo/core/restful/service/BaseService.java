package com.malibentoo.core.restful.service;

import com.malibentoo.core.annotations.crud.AnnotationProvider;
import com.malibentoo.core.annotations.crud.ValidateEntityBefore;
import com.malibentoo.core.restful.enums.BaseServiceMethod;
import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.core.validator.DtoValidator;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.exception.api.ApiExceptionFactory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

public abstract class BaseService<DtoType extends RestfulDTO> implements AnnotationProvider {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationContext applicationContext;

    private DtoValidator createValidator;
    private DtoValidator updateValidator;

    public BaseService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // Abstracts
    protected abstract DtoType doCreate(DtoType obj);
    protected abstract DtoType doUpdate(DtoType obj) throws ApiException;
    protected abstract DtoType doGetById(@Nonnull Integer id) throws ApiException;
    protected abstract void doDelete(@Nonnull Integer id) throws ApiException;

    // Proxy
    @SuppressWarnings("unused")
    @Transactional
    public DtoType create(DtoType obj) throws ApiException {
        if (createValidator != null) {
            createValidator.validateWrite(obj);
        }
        return doCreate(obj);
    }

    @SuppressWarnings("unused")
    @Transactional
    public DtoType update(DtoType obj) throws ApiException {
        if (updateValidator != null) {
            updateValidator.validateWrite(obj);
        }
        return doUpdate(obj);
    }

    @SuppressWarnings("unused")
    @Transactional
    public void delete(Integer id) throws ApiException {
        assertIdNotNull(id);
        doDelete(id);
    }

    @SuppressWarnings("unused")
    public DtoType getById(Integer id) throws ApiException {
        assertIdNotNull(id);
        return doGetById(id);
    }

    private static void assertIdNotNull(Integer id) throws ApiException {
        if (id == null) {
            throw ApiExceptionFactory.propertyRequired("Id");
        }
    }

    // Utils
    @Nullable
    private DtoValidator getValidatorBeanForAnnotation(@Nonnull Annotation annotation) {
        if (applicationContext == null) {
            logger.warn("ApplicationContext is null");
            return null;
        }

        if (annotation instanceof ValidateEntityBefore validateEntityBeforeAnnotation) {
            return applicationContext.getBean(validateEntityBeforeAnnotation.value(), DtoValidator.class);
        }

        return null;
    }

    private void applyValidator(BaseServiceMethod method, Consumer<ValidateEntityBefore> consumer) {
        getAnnotationForMethod(method.getMethod(), ValidateEntityBefore.class).ifPresent(consumer);
    }

    @PostConstruct
    @SuppressWarnings("unused")
    private void postConstruct() {
        applyValidator(BaseServiceMethod.DO_CREATE, validateEntityBefore ->
                createValidator = getValidatorBeanForAnnotation(validateEntityBefore));

        applyValidator(BaseServiceMethod.DO_UPDATE, validateEntityBefore ->
                updateValidator = getValidatorBeanForAnnotation(validateEntityBefore));
    }
}
