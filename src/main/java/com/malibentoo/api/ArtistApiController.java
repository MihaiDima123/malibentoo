package com.malibentoo.api;

import com.malibentoo.core.restful.RestfulEntity;
import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.facade.artist.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/artist")
public class ArtistApiController {
    private final ArtistService artistService;

    public ArtistApiController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<RestfulEntity> createArtist(@RequestBody ArtistDTO artistDTO) {
        return ResponseEntity.ok(artistService.create(artistDTO));
    }
}
