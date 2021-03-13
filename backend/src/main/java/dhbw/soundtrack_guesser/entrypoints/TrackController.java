package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.entrypoints.exceptions.TracksNotFoundException;
import dhbw.soundtrack_guesser.models.Track;
import dhbw.soundtrack_guesser.service.TrackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/track")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("")
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping("/{id}")
    public Track getMovieById(@PathVariable String id) {
        return trackService.getTrackByID(id);
    }

    @GetMapping("/for/{movieID}")
    public List<Track> getTracksForMovie(@PathVariable int movieID) {
        List<Track> movies = trackService.findForMovie(movieID);
        if (movies.isEmpty()) {
            throw new TracksNotFoundException();
        }
        return movies;
    }

    @GetMapping("/search/{query}")
    public List<Track> getSearch(@PathVariable String query) {
        return trackService.search(query);
    }
}
