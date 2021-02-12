package com.example.demo.dataproviders;

import com.example.demo.models.Movie;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class MockMovieProvider implements MovieProvider {

    @Override
    public List<Movie> getAllMovies() {
        return List.of(
                new Movie(
                        120,
                        "The Lord of the Rings: The Fellowship of the Ring",
                        "/p4UokEk2XnjjRTdXGe6DLYXlbI1.jpg",
                        List.of("Adventure", "Fantasy", "Action"), "Young hobbit Frodo Baggins, after inheriting a mysterious ring from his uncle Bilbo, must leave his home in order to keep it from falling into the hands of its evil creator. Along the way, a fellowship is formed to protect the ringbearer and make sure that the ring arrives at its final destination: Mt. Doom, the only place where it can be destroyed."
                ),
                new Movie(
                        278,
                        "The Shawshank Redemption",
                        "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                        List.of("Drama", "Crime"),
                        "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope."
                ),
                new Movie(
                        680,
                        "Pulp Fiction",
                        "/plnlrtBUULT0rh3Xsjmpubiso3L.jpg",
                        List.of("Thriller", "Crime"),
                        "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time."
                ),
                new Movie(
                        13,
                        "Forrest Gump",
                        "/h5J4W4veyxMXDMjeNxZI46TsHOb.jpg",
                        List.of("Comedy", "Drama", "Romance"),
                        "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him."
                ),
                new Movie(
                        16869,
                        "Inglourious Basterds",
                        "/7sfbEnaARXDDhKm0CZ7D7uc2sbo.jpg",
                        List.of("Drama", "Action", "Thriller", "War"),
                        "In Nazi-occupied France during World War II, a group of Jewish-American soldiers known as \"The Basterds\" are chosen specifically to spread fear throughout the Third Reich by scalping and brutally killing Nazis. The Basterds, lead by Lt. Aldo Raine soon cross paths with a French-Jewish teenage girl who runs a movie theater in Paris which is targeted by the soldiers"
                )
        );
    }
}
