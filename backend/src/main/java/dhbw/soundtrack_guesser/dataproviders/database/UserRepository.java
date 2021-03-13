package dhbw.soundtrack_guesser.dataproviders.database;

import dhbw.soundtrack_guesser.dataproviders.database.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query("select u from users as u  where u.username = ?1")
    Optional<UserEntity> findOneByUsername(String username);
}
