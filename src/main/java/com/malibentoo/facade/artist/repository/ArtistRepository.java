package com.malibentoo.facade.artist.repository;

import com.malibentoo.data.entities.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
}
