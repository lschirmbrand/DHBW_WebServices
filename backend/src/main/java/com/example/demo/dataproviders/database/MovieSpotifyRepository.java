package com.example.demo.dataproviders.database;

import com.example.demo.entities.MovieSpotifyDBEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSpotifyRepository extends CrudRepository<MovieSpotifyDBEntity, Integer> {

}
