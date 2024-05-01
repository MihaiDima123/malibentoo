package com.malibentoo.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Producer producer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Genre> songGenres;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Artist> songPerformers;
}
