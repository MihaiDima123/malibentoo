package com.malibentoo.data.dto.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.facade.artist.transformers.ArtistDtoTransformer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDTO extends RestfulDTO {
    private static final ArtistDtoTransformer ARTIST_DTO_TRANSFORMER = new ArtistDtoTransformer();
    private Integer id;
    private String name;

    public static ArtistDtoTransformer transformer() {
        return ARTIST_DTO_TRANSFORMER;
    }
}
