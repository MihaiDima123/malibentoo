package com.malibentoo.core.restful.transformer;

public interface DtoTransformer<Dto, Entity> {
    Dto from(Entity to);
    Entity to(Dto from);
}
