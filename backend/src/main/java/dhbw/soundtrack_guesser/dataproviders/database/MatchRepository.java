package dhbw.soundtrack_guesser.dataproviders.database;

import dhbw.soundtrack_guesser.dataproviders.database.entities.MatchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {

}
