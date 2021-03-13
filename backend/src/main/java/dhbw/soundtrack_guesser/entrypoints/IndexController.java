package dhbw.soundtrack_guesser.entrypoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public Map<String, String> getHelloWorld() {
        Map<String, String> res = new HashMap<>();
        res.put("message", "Soundtrack-Guesser API works!");

        return res;
    }
}
