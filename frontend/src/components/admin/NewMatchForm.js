import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import { FaCheck, FaTimes } from 'react-icons/fa';
import { Redirect } from 'react-router-dom';
import Sound from 'react-sound';

import Movie from './Movie';
import Track from './Track';

import './NewMatchForm.css';

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
            playingIndex: -1,
            url: '',
        };
    }

    componentDidMount() {
        this.setState(this.props.location.state);
    }

    handleMovieInputChange = (event) => {
        const query = event.target.value;
        this.setState({ selectedMovie: { title: query, id: 0 } });
        this.searchMovies(query);
    };

    searchMovies = (query) => {
        if (query.trim() === '') {
            this.setState({ movies: [] });
            return;
        }
        this.setState({ loadingMovies: true });
        const url = encodeURI('http://localhost:8081/movies/search/' + query);
        fetch(url)
            .then((res) => {
                return res.json();
            })
            .then((movies) => {
                this.setState({ loadingMovies: false, movies: movies || [] });
            })
            .catch((err) => console.error(err));
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
        this.setState({
            selectedTrack: { name: query, id: 0 },
            url: '',
            playingIndex: -1,
        });
        this.searchTracks(query);
    };

    searchTracks = (query) => {
        if (query.trim() === '') {
            this.setState({ tracks: [] });
            return;
        }
        this.setState({ loadingTracks: true });
        const url =
            'http://localhost:8081/spotify/search/' +
            query.replaceAll(' ', '+');
        fetch(url)
            .then((res) => res.json())
            .then((tracks) =>
                this.setState({ loadingTracks: false, tracks: tracks || [] })
            )
            .catch((err) => console.error(err));
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

    play = (url, index) => {
        if (this.state.playingIndex === index) {
            this.setState({ playingIndex: -1, url: '' });
        } else {
            this.setState({ mp3url: url, playingIndex: index });
        }
    };

    submit = () => {
        const body = {
            tmdbID: this.state.selectedMovie.id,
            spotifyID: this.state.selectedTrack.id,
        };
        fetch('http://localhost:8081/match', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        })
            .then((res) => res.json())
            .then((data) =>
                this.setState({
                    matches: [...this.state.matches, data],
                    selectedMovie: { id: 0, title: '' },
                    selectedTrack: { id: '', name: '' },
                    redirect: true,
                })
            )
            .catch((err) => console.error(err));
    };

    abort = () => {
        this.setState({ redirect: true });
    };

    render() {
        return (
            <div className="new-match">
                <Sound
                    url={this.state.mp3url}
                    playStatus={
                        this.state.playingIndex >= 0 ? 'PLAYING' : 'STOPPED'
                    }
                />
                <h1>Add new Match</h1>
                {this.state.redirect && (
                    <Redirect
                        to={{
                            pathname: '/admin',
                            state: {
                                matches: this.state.matches,
                                loading: false,
                            },
                        }}
                    />
                )}
                <div className="new-form">
                    {/* Moive input */}
                    <div className="holder">
                        <input
                            id="movie"
                            placeholder="Movie"
                            onChange={this.handleMovieInputChange}
                            className="movie-search"
                            value={this.state.selectedMovie.title}
                        />
                        <div
                            className={
                                'result-list movie-result' +
                                (this.state.movies.length === 0
                                    ? ' hidden'
                                    : '')
                            }
                        >
                            {this.state.movies.map((movie) => (
                                <div className="result" key={movie.id}>
                                    <Movie movie={movie} />
                                    <Button
                                        onClick={(e) => this.selectMovie(movie)}
                                    >
                                        <FaCheck />
                                    </Button>
                                </div>
                            ))}
                        </div>
                    </div>
                    {/* Soundtrack input */}
                    <div className="holder">
                        <input
                            id="track"
                            placeholder="Soundtrack"
                            onChange={this.handleTrackInputChange}
                            className="track-search"
                            value={this.state.selectedTrack.name}
                        />
                        <div
                            className={
                                'result-list track-result' +
                                (this.state.tracks.length === 0
                                    ? ' hidden'
                                    : '')
                            }
                        >
                            {this.state.tracks.map((track, index) => (
                                <div className="result" key={track.id}>
                                    <Track
                                        track={track}
                                        play={() =>
                                            this.play(track.previewURL, index)
                                        }
                                        playing={
                                            this.state.playingIndex === index
                                        }
                                    />
                                    <Button
                                        onClick={(e) => this.selectTrack(track)}
                                    >
                                        <FaCheck />
                                    </Button>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
                <Button
                    variant="primary"
                    className="submit-btn"
                    onClick={this.submit}
                >
                    <FaCheck /> Submit
                </Button>{' '}
                <Button
                    variant="danger"
                    className="submit-btn"
                    onClick={this.abort}
                >
                    <FaTimes /> Abort
                </Button>
            </div>
        );
    }
}
