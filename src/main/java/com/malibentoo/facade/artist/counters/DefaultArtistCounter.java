package com.malibentoo.facade.artist.counters;

import com.malibentoo.core.restful.repo.EntityCounter;
import com.malibentoo.facade.artist.repository.ArtistRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DefaultArtistCounter implements EntityCounter {
    private final ArtistRepository artistRepository;

    public DefaultArtistCounter(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public int countByName(String name, boolean includeInactive) {
        Objects.requireNonNull(name);
        if (includeInactive) {
            return artistRepository.getCountByNameIncludingInactive(name.trim());
        }

        return artistRepository.getCountByName(name.trim());
    }

    @Override
    public boolean existsByName(String name, boolean includeInactive) {
        return countByName(name, includeInactive) > 0;
    }

    @Override
    public boolean existsById(int id, boolean includeInactive) {
        if (includeInactive) {
            return artistRepository.getCountByIdIncludingInactive(id) > 0;
        }

        return artistRepository.getCountById(id) > 0;
    }
}
