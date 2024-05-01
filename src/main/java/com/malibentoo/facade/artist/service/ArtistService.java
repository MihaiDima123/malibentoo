package com.malibentoo.facade.artist.service;

import com.malibentoo.core.restful.BaseService;
import com.malibentoo.data.entities.Artist;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends BaseService<Artist> {
    @Override
    protected Artist doCreate(Artist obj) {
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
