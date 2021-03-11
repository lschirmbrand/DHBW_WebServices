package dhbw.soundtrack_guesser.dataproviders.database;

import dhbw.soundtrack_guesser.dataproviders.database.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
