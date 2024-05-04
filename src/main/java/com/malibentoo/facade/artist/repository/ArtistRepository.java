package com.malibentoo.facade.artist.repository;

import com.malibentoo.data.entities.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    @Query("select count(0) " +
            "from Artist a " +
            "where upper(a.name) = upper(:name) ")
    int getCountByNameIncludingInactive(String name);

    @Query("select count(0) " +
            "from Artist a " +
            "where upper(a.name) = upper(:name) and a.deletedAt is null ")
    int getCountByName(String name);
}
