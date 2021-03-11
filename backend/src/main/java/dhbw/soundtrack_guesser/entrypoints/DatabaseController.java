package dhbw.soundtrack_guesser.entrypoints;

import dhbw.soundtrack_guesser.dataproviders.database.UserRepository;
import dhbw.soundtrack_guesser.dataproviders.database.entities.UserEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/db")
public class DatabaseController {

    private final UserRepository userRepository;

    public DatabaseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/user/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        UserEntity n = new UserEntity();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "user/all")
    public @ResponseBody
    Iterable<UserEntity> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}

