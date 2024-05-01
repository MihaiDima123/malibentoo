package com.malibentoo.core.restful;

public abstract class RestfulDTO {
    protected abstract RestfulEntity doToEntity();

    @SuppressWarnings("unchecked")
    <EntityType extends RestfulEntity> EntityType toEntity() {
        return (EntityType) doToEntity();
    }
}
