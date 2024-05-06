package com.malibentoo.api;

import com.malibentoo.core.restful.objects.RestfulDTO;
import com.malibentoo.data.dto.artist.ArtistDTO;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.facade.artist.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/artist")
public class ArtistApiController {
    private final ArtistService artistService;

    public ArtistApiController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable("id") Integer id) throws ApiException {
        return ResponseEntity.ok(artistService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RestfulDTO> createArtist(@RequestBody ArtistDTO artistDTO) throws ApiException {
        return ResponseEntity.ok(artistService.create(artistDTO));
    }

    @PatchMapping
    private ResponseEntity<RestfulDTO> updateArtist(@RequestBody ArtistDTO artistDTO) throws ApiException {
        return ResponseEntity.ok(artistService.update(artistDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") Integer id) throws ApiException {
        artistService.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
