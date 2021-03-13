package dhbw.soundtrack_guesser.service;

import dhbw.soundtrack_guesser.dataproviders.match.MatchRepository;
import dhbw.soundtrack_guesser.dataproviders.match.MatchEntity;
import dhbw.soundtrack_guesser.dataproviders.track.TrackProvider;
import dhbw.soundtrack_guesser.models.Track;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TrackService {
    private final TrackProvider trackProvider;
    @Autowired
    MatchRepository matchRepository;

    public TrackService(TrackProvider trackProvider) {
        this.trackProvider = trackProvider;
    }

    public List<Track> getAllTracks() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), true)
                .map(MatchEntity::getSpotifyID)
                .map(trackProvider::getTrack)
                .collect(Collectors.toList());
    }

    public Track getTrackByID(String id) {
        return trackProvider.getTrack(id);
    }

    public List<Track> findForMovie(int movieID) {
        return StreamSupport.stream(matchRepository.findAllByMovie(movieID).spliterator(), true)
                .map(MatchEntity::getSpotifyID)
                .map(trackProvider::getTrack)
                .collect(Collectors.toList());
    }

    public List<Track> search(String query) {
        return trackProvider.searchTrack(query);
    }
}
