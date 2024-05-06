package com.malibentoo.malibentoo.unit.restful.helpers.baseservice;

import com.malibentoo.core.restful.objects.RestfulDTO;

import java.util.Objects;

public class BaseTestDto extends RestfulDTO {
    public static String DEFAULT_TEST_INSTANCE_FIELD_VALUE = "Test";
    private Integer id;
    private String field;


    public static BaseTestDto getDefaultTestInstance() {
        return new BaseTestDto()
                .setField(DEFAULT_TEST_INSTANCE_FIELD_VALUE)
                .setId(1);
    }

    public String getField() {
        return field;
    }
    public BaseTestDto setField(String field) {
        this.field = field;
        return this;
    }

    public Integer getId() {
        return id;
    }
    public BaseTestDto setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTestDto that = (BaseTestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, field);
    }

    @Override
    public String toString() {
        return "BaseTestDto{" +
                "id=" + id +
                ", field='" + field + '\'' +
                '}';
    }
}
