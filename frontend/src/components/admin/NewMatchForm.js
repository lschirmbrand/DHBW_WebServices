import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import { Redirect } from 'react-router-dom';
import Movie from './Movie';
import Track from './Track';

export default class NewMatchForm extends Component {
    constructor() {
        super();
        this.state = {
            matches: [],
            loadingMovies: false,
            movies: [],
            selectedMovie: {
                title: '',
                id: 0,
            },
            loadingTracks: false,
            tracks: [],
            selectedTrack: {
                name: '',
                id: '',
            },
            redirect: false,
        };
    }

    componentDidMount() {
        this.setState(this.props.location.state);
        console.log(this.state);
        console.log(this.props);
    }

    handleMovieInputChange = (event) => {
        const query = event.target.value;
        this.setState({ selectedMovie: { title: query, id: 0 } });
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
    };

    selectMovie = (movie) => {
        this.setState({
            selectedMovie: {
                title: movie.title,
                id: movie.id,
            },
            movies: [],
        });
    };

    handleTrackInputChange = (event) => {
        const query = event.target.value;
        this.setState({ selectedTrack: { name: query, id: 0 } });
        this.searchTracks(query);
    };

    searchTracks = async (query) => {
        if (query.trim() == '') {
            this.setState({ tracks: [] });
            return;
        }
        this.setState({ loadingTracks: true });
        const url =
            'http://localhost:8081/spotify/search/' +
            query.replaceAll(' ', '+');
        const res = await fetch(url);
        const tracks = await res.json();
        this.setState({ loadingTracks: false, tracks: tracks || [] });
    };

    selectTrack = (track) => {
        this.setState({
            selectedTrack: {
                name: track.name,
                id: track.id,
            },
            tracks: [],
        });
    };

    submit = async () => {
        const body = {
            tmdbID: this.state.selectedMovie.id,
            spotifyID: this.state.selectedTrack.id,
        };
        const res = await fetch('http://localhost:8081/match', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        });
        const data = await res.json();
        this.setState({
            matches: [...this.state.matches, data],
            selectedMovie: { id: 0, title: '' },
            selectedTrack: { id: '', name: '' },
            redirect: true,
        });
    };

    render() {
        return (
            <div className="new-form">
                {this.state.redirect && (
                    <Redirect
                        to={{
                            pathname: '/admin',
                            state: { matches: this.state.matches },
                        }}
                    />
                )}
                <div className="holder">
                    <input
                        id="movie"
                        placeholder="movie"
                        onChange={this.handleMovieInputChange}
                        className="movie-search"
                        value={this.state.selectedMovie.title}
                    />
                    <div className="result-list movie-result">
                        {this.state.movies.map((movie) => (
                            <div className="result" key={movie.id}>
                                <Movie movie={movie} />
                                <button
                                    onClick={(e) => this.selectMovie(movie)}
                                >
                                    ✓
                                </button>
                            </div>
                        ))}
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
                        {this.state.tracks.map((track) => (
                            <div className="result" key={track.id}>
                                <Track track={track} />
                                <button
                                    onClick={(e) => this.selectTrack(track)}
                                >
                                    ✓
                                </button>
                            </div>
                        ))}
                    </div>
                </div>
                <Button
                    variant="primary"
                    className="submit-btn"
                    onClick={this.submit}
                >
                    Submit
                </Button>
            </div>
        );
    }
}
