package com.example.demo.dataproviders.database;

import com.example.demo.entities.MatchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {

}
