package dhbw.soundtrack_guesser.dataproviders.track;

import dhbw.soundtrack_guesser.models.Track;

import java.util.List;

public interface TrackProvider {
    Track getTrack(String id);

    List<Track> searchTrack(String query);
}
