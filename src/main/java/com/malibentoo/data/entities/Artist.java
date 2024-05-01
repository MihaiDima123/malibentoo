package com.malibentoo.data.entities;

import com.malibentoo.core.restful.RestfulDTO;
import com.malibentoo.core.restful.RestfulEntity;
import com.malibentoo.data.dto.artist.ArtistDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Artist implements RestfulEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Override
    public RestfulDTO toDTO() {
        return ArtistDTO.from(this);
    }
}
