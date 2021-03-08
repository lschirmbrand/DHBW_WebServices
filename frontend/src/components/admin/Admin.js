import React from "react"
import "../../styles/Admin.css"
import Movie from "./Movie"
import Track from "./Track"


class Admin extends React.Component {
    constructor() {
        super()
        this.state = {
            matches: [],
            loadingMovies: false,
            movies: [],
            selectedMovie: {
                title: '',
                id: 0
            },
            loadingTracks: false,
            tracks: [],
            selectedTrack: {
                name: '',
                id: ''
            }
        }
    }

    componentDidMount() {
        fetch('http://localhost:8081/match')
            .then(response => response.json())
            .then(data => { this.setState({ matches: data }) })
    }

    handleMovieInputChange = (event) => {
        const query = event.target.value;
        this.setState({ selectedMovie: { title: query, id: 0 } })
        this.searchMovies(query);
    };

    searchMovies = async (query) => {
        if (query.trim() === '') {
            this.setState({ movies: [] });
            return;
        }
        this.setState({ loadingMovies: true });
        const url = encodeURI('http://localhost:8081/movies/search/' + query);
        const res = await fetch(url);
        const movies = await res.json();
        this.setState({ loadingMovies: false, movies: movies || [] });
    }

    selectMovie = (movie) => {
        this.setState({
            selectedMovie: {
                title: movie.title,
                id: movie.id
            }, movies: []
        })
    }

    handleTrackInputChange = (event) => {
        const query = event.target.value;
        this.setState({ selectedTrack: { name: query, id: 0 } })
        this.searchTracks(query);
    };

    searchTracks = async (query) => {
        if (query.trim() == '') {
            this.setState({ tracks: [] });
            return;
        }
        this.setState({ loadingTracks: true });
        const url = 'http://localhost:8081/spotify/search/' + query.replaceAll(' ', '+');
        console.log(url)
        const res = await fetch(url);
        const tracks = await res.json();
        this.setState({ loadingTracks: false, tracks: tracks || [] });
    }

    selectTrack = (track) => {
        this.setState({
            selectedTrack: {
                name: track.name,
                id: track.id
            }, tracks: []
        })
    }

    submit = async () => {
        console.log(this.state);
        const body = {
            tmdbID: this.state.selectedMovie.id,
            spotifyID: this.state.selectedTrack.id
        }

        console.log(body)
        const res = await fetch('http://localhost:8081/match', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        })
        const data = await res.json();
        this.setState({ matches: [...this.state.matches, data] })
    }

    render() {
        return (
            <div className="admin">
                <div id="table">
                    <table className="content-table">
                        <thead>
                            <tr>
                                <th>Movie</th>
                                <th>Soundtrack</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.matches.map(match => (
                                <tr key={match.id}>
                                    <td><Movie movie={match.movie} /></td>
                                    <td><Track track={match.track} /></td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                <div className="new-form">
                    <div className="holder">
                        <input
                            id="movie"
                            placeholder="movie"
                            onChange={this.handleMovieInputChange}
                            className="movie-search"
                            value={this.state.selectedMovie.title}
                        />
                        <div className="result-list movie-result">
                            {this.state.movies.map(movie =>
                                <div
                                    className="result"
                                    key={movie.id}
                                >
                                    <Movie movie={movie} />
                                    <button
                                        onClick={e => this.selectMovie(movie)}
                                    >✓</button>
                                </div>
                            )}

                        </div>
                    </div>
                    <div className="holder">
                        <input
                            id="track"
                            placeholder="soundtrack"
                            onChange={this.handleTrackInputChange}
                            className="track-search"
                            value={this.state.selectedTrack.name}
                        />
                        <div className="result-list track-result">
                            {this.state.tracks.map(track =>
                                <div
                                    className="result"
                                    key={track.id}
                                >
                                    <Track track={track} />
                                    <button
                                        onClick={e => this.selectTrack(track)}
                                    >✓</button>
                                </div>

                            )}
                        </div>
                    </div>
                    <button
                        className="submit-btn"
                        onClick={this.submit}
                    >Submit</button>
                </div >
            </div >
        )
    }
}

export default Admin