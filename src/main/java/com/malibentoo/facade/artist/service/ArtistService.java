package com.malibentoo.facade.artist.service;

import com.malibentoo.core.annotations.crud.ValidateEntityBefore;
import com.malibentoo.core.restful.service.BaseService;
import com.malibentoo.data.entities.Artist;
import com.malibentoo.exception.api.ApiException;
import com.malibentoo.exception.api.ApiExceptionFactory;
import com.malibentoo.facade.artist.counters.DefaultArtistCounter;
import com.malibentoo.facade.artist.repository.ArtistRepository;
import jakarta.annotation.Nonnull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends BaseService<Artist> {
    private final ArtistRepository artistRepository;
    private final DefaultArtistCounter defaultArtistCounter;

    public ArtistService(ApplicationContext applicationContext,
                         ArtistRepository artistRepository,
                         DefaultArtistCounter defaultArtistCounter) {
        super(applicationContext);
        this.artistRepository = artistRepository;
        this.defaultArtistCounter = defaultArtistCounter;
    }

    @Override
    protected Artist doGetById(@Nonnull Integer id) throws ApiException {
        return getOrElseThrow(id);
    }

    @Override
    @ValidateEntityBefore(value = "artistWriteValidator")
    protected Artist doCreate(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    @ValidateEntityBefore(value = "artistWriteValidator")
    protected Artist doUpdate(Artist artistRequest) throws ApiException {
        checkCountAndThrow(artistRequest.getId());
        return artistRepository.save(artistRequest);
    }

    @Override
    protected void doDelete(@Nonnull Integer id) throws ApiException {
        assertArtistExists(id, () ->
                artistRepository.removeArtistById(id));
    }

    private Artist getOrElseThrow(@Nonnull Integer id) throws ApiException {
        return artistRepository.getById(id).orElseThrow(() ->
                ApiExceptionFactory.propertyNotFound("Artist with id " + id));
    }

    private void assertArtistExists(@Nonnull Integer id, Runnable successfullyAssertion) throws ApiException {
        checkCountAndThrow(id);
        successfullyAssertion.run();
    }

    private void checkCountAndThrow(Integer id) throws ApiException {
        if (!defaultArtistCounter.existsById(id)) {
            throw ApiExceptionFactory.propertyNotFound("Artist with id " + id);
        }
    }
}
