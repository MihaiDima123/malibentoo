package com.malibentoo.malibentoo.unit.restful;

import com.malibentoo.exception.api.ApiException;
import com.malibentoo.malibentoo.unit.restful.helpers.baseservice.BaseServiceTestImpl;
import com.malibentoo.malibentoo.unit.restful.helpers.baseservice.BaseTestDto;
import com.malibentoo.malibentoo.unit.restful.helpers.baseservice.BaseTestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseServiceTest {
    @Mock
    ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);

    BaseServiceTestImpl baseServiceTest = new BaseServiceTestImpl(applicationContext);

    BaseTestDto defaultDtoInstance;

    @BeforeEach
    public void setUpBeforeClass() {
        defaultDtoInstance = BaseTestDto.getDefaultTestInstance();
        Mockito.when(applicationContext.getBean(BaseServiceTestImpl.MOCK_BEAN_NAME))
                .thenReturn(new BaseTestValidator());
    }

    @Test
    public void testCreateBaseService() throws ApiException {
        assertEquals(
                baseServiceTest.create(defaultDtoInstance),
                defaultDtoInstance
        );
    }

    @Test
    public void testUpdateBaseService() throws ApiException {
        assertEquals(
                baseServiceTest.update(defaultDtoInstance),
                defaultDtoInstance
        );
    }

    @Test
    public void testDeleteBaseService() throws ApiException {
        baseServiceTest.delete(defaultDtoInstance.getId());
    }

    @Test
    public void testGetBaseService() throws ApiException {
        baseServiceTest.delete(defaultDtoInstance.getId());
    }

    @Test
    public void testCreateShouldValidateNameNullAndEmpty() {
        defaultDtoInstance.setField(null);
        Exception exception = assertThrows(ApiException.class, () -> baseServiceTest.create(defaultDtoInstance));
        assertEquals(exception.getMessage(), BaseTestValidator.BASE_VALIDATOR_EMPTY_ERROR_MESSAGE);
    }

    @Test
    public void testUpdateShouldValidateNameNullAndEmpty() {
        defaultDtoInstance.setField(null);
        Exception exception = assertThrows(ApiException.class, () -> baseServiceTest.update(defaultDtoInstance));
        assertEquals(exception.getMessage(), BaseTestValidator.BASE_VALIDATOR_EMPTY_ERROR_MESSAGE);
    }

    @Test
    public void testDeleteShouldThrowErrorIfIdNull() {
        assertThrows(ApiException.class, () -> baseServiceTest.delete(null));
    }

    @Test
    public void testGetByIdShouldThrowErrorIfIdNull() {
        assertThrows(ApiException.class, () -> baseServiceTest.getById(null));
    }
}
