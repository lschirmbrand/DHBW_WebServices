package com.example.demo.repositories;

import com.example.demo.entities.MovieSpotifyDBEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieSpotifyRepository extends CrudRepository<MovieSpotifyDBEntity, Integer> {

}
