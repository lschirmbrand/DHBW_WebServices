package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.dataproviders.match.MatchEntity;
import dhbw.soundtrack_guesser.entrypoints.exceptions.DuplicateTrackException;
import dhbw.soundtrack_guesser.models.Match;
import dhbw.soundtrack_guesser.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping()
    public List<Match> getAllMatches() {
        return matchService.getAll();
    }

    @GetMapping("/{id}")
    public Match getMatchByID(@PathVariable int id) {
        return matchService.getMatch(id);
    }

    @PostMapping()
    public Match addMatch(@RequestBody MatchEntity matchEntity) {
        List<String> trackIds = matchService.getAll().stream().map(match -> match.getTrack().getId()).collect(Collectors.toList());
        if (trackIds.contains(matchEntity.getSpotifyID())) {
            throw new DuplicateTrackException();
        }
        return matchService.addMatch(matchEntity);
    }

    @PatchMapping()
    public MatchEntity updateMatch(@RequestBody MatchEntity matchEntity) {
        return matchService.editMatch(matchEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchService.deleteMatch(id);
    }
}
