package com.malibentoo.core.restful.objects;

public abstract class RestfulDTO {
    protected abstract RestfulEntity doToEntity();

    @SuppressWarnings("unchecked")
    public <EntityType extends RestfulEntity> EntityType toEntity() {
        return (EntityType) doToEntity();
    }
}
