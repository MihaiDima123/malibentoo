package com.malibentoo.facade.artist.service;

import com.malibentoo.core.annotations.crud.ValidateEntityBefore;
import com.malibentoo.core.restful.BaseService;
import com.malibentoo.data.entities.Artist;
import com.malibentoo.facade.artist.repository.ArtistRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends BaseService<Artist> {
    private final ArtistRepository artistRepository;

    public ArtistService(ApplicationContext applicationContext,
                         ArtistRepository artistRepository) {
        super(applicationContext);
        this.artistRepository = artistRepository;
    }

    @Override
    @ValidateEntityBefore(value = "artistWriteValidator")
    protected Artist doCreate(Artist artist) {
        artistRepository.save(artist);
        return null;
    }

    @Override
    protected Artist doUpdate(Artist obj) {
        return null;
    }

    @Override
    protected Artist doGetById(Integer id) {
        return null;
    }

    @Override
    public void delete(Artist obj) {

    }
}
