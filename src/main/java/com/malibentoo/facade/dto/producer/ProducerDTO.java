package com.malibentoo.facade.dto.producer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.facade.entities.Producer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProducerDTO {
    private Integer id;
    private String name;

    public static ProducerDTO from(Producer producer) {
        return ProducerDTO
                .builder()
                .id(producer.getId())
                .name(producer.getName())
                .build();
    }
}
