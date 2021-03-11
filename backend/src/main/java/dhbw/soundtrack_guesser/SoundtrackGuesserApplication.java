package dhbw.soundtrack_guesser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SoundtrackGuesserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoundtrackGuesserApplication.class, args);
    }

}
