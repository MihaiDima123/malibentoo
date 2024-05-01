package com.malibentoo.core.restful;

public abstract class BaseService<EntityType extends RestfulEntity> {
    protected abstract EntityType doCreate(EntityType obj);
    protected abstract EntityType doUpdate(EntityType obj);
    protected abstract EntityType doGetById(Integer id);

    public abstract void delete(EntityType obj);

    public RestfulEntity create(RestfulDTO obj) {
        return doCreate(obj.toEntity());
    }

    public RestfulEntity update(RestfulDTO obj) {
        return doUpdate(obj.toEntity());
    }

    public RestfulDTO getById(Integer id) {
        return doGetById(id).toDTO();
    }
}
