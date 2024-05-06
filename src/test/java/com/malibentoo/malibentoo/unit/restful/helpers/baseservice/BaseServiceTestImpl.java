package com.malibentoo.malibentoo.unit.restful.helpers.baseservice;

import com.malibentoo.core.annotations.crud.ValidateEntityBefore;
import com.malibentoo.core.restful.service.BaseService;
import jakarta.annotation.Nonnull;
import org.springframework.context.ApplicationContext;

// Just testing the base implementation for the BaseService, not the actual logic behind anything
public class BaseServiceTestImpl extends BaseService<BaseTestDto> {
    public static final String MOCK_BEAN_NAME = "base";

    public BaseServiceTestImpl(ApplicationContext applicationContext) {
        super(applicationContext);
        setValidators();
    }

    private void setValidators() {
        try {
            addValidatorField("createValidator");
            addValidatorField("updateValidator");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addValidatorField(String name) {
        try {
            var field = BaseService.class.getDeclaredField(name);
            field.setAccessible(true);
            field.set(this, new BaseTestValidator());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @ValidateEntityBefore(MOCK_BEAN_NAME)
    protected BaseTestDto doCreate(BaseTestDto obj) {
        return obj;
    }

    @Override
    @ValidateEntityBefore(MOCK_BEAN_NAME)
    protected BaseTestDto doUpdate(BaseTestDto obj) {
        return obj;
    }

    @Override
    protected BaseTestDto doGetById(@Nonnull Integer id) {
        return BaseTestDto.getDefaultTestInstance();
    }

    @Override
    protected void doDelete(@Nonnull Integer id) {
    }
}
