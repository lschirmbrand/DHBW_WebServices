package dhbw.soundtrack_guesser.dataproviders.match;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {
    @Query("select m from matches m where m.movieID = ?1")
    Iterable<MatchEntity> findAllByMovie(int movieID);

    @Query("select m from matches m where m.trackID = ?1")
    Iterable<MatchEntity> findAllByTrack(String trackID);
}
