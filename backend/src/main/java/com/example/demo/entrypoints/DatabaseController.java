package com.example.demo.entrypoints;

import com.example.demo.entities.MovieSpotifyDBEntity;
import com.example.demo.entities.User;
import com.example.demo.dataproviders.database.MovieSpotifyRepository;
import com.example.demo.dataproviders.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/db")
public class DatabaseController {

    private final UserRepository userRepository;

    private final MovieSpotifyRepository movieSpotifyRepository;

    public DatabaseController(UserRepository userRepository, MovieSpotifyRepository movieSpotifyRepository) {
        this.userRepository = userRepository;
        this.movieSpotifyRepository = movieSpotifyRepository;
    }

    @PostMapping(path = "/user/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "user/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}

