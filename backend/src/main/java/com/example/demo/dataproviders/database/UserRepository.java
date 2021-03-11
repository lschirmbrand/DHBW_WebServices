package com.example.demo.dataproviders.database;

import com.example.demo.dataproviders.database.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
