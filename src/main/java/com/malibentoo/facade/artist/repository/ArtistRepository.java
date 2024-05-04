package com.malibentoo.facade.artist.repository;

import com.malibentoo.data.entities.Artist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    @Query("select count(0) " +
            "from Artist a " +
            "where upper(a.name) = upper(:name) ")
    int getCountByNameIncludingInactive(String name);

    @Query("select count(0) " +
            "from Artist a " +
            "where upper(a.name) = upper(:name) and a.deletedAt is null ")
    int getCountByName(String name);

    @Query("select count(0) " +
            "from Artist a " +
            "where a.id = :id ")
    int getCountByIdIncludingInactive(Integer id);

    @Query("select count(0) " +
            "from Artist a " +
            "where a.id = :id and a.deletedAt is null ")
    int getCountById(Integer id);

    @Query("select a " +
            "from Artist a " +
            "where a.id = :id and a.deletedAt is null ")
    Optional<Artist> getById(Integer id);

    @Modifying
    @Query("update Artist " +
            "set deletedAt = CURRENT_TIMESTAMP " +
            "where id = :id and deletedAt is null")
    void removeArtistById(Integer id);
}
