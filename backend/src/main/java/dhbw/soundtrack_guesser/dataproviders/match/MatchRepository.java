package dhbw.soundtrack_guesser.dataproviders.match;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {
    @Query("select m from matches m where m.tmdbID = ?1")
    Iterable<MatchEntity> findAllByMovie(int tmdbID);

    @Query("select m from matches m where m.spotifyID = ?!")
    Iterable<MatchEntity> findAllByTrack(String spotifyID);
}
