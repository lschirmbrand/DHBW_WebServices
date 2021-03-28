import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import { FaCheck, FaTimes } from 'react-icons/fa';
import { Redirect } from 'react-router-dom';
import Sound from 'react-sound';

import Movie from './Movie';
import Track from './Track';

import './NewMatchForm.css';

export default function NewMatchForm() {
    const serverURL =
        'http://' + window._env_.SERVER_HOST + ':' + window._env_.SERVER_PORT;

    const [movies, setMovies] = useState([]);
    const [selectedMovie, setSelectedMovie] = useState({ title: '', id: 0 });
    const [tracks, setTracks] = useState([]);
    const [selectedTrack, setSelectedTrack] = useState({ name: '', id: '' });
    const [redirect, setRedirect] = useState(false);
    const [playingIndex, setPlayingIndex] = useState(-1);
    const [mp3url, setMp3url] = useState('');

    const handleMovieInputChange = (event) => {
        const query = event.target.value;
        setSelectedMovie({ title: query, id: 0 });
        searchMovies(query);
    };

    const searchMovies = (query) => {
        if (query.trim() === '') {
            setMovies([]);
            return;
        }
        const url = encodeURI(serverURL + '/movie/search/' + query);
        fetch(url)
            .then((res) => {
                return res.json();
            })
            .then((data) => setMovies(data || []))
            .catch((err) => console.error(err));
    };

    const selectMovie = (movie) => {
        setSelectedMovie({
            title: movie.title,
            id: movie.id,
        });
        setMovies([]);
    };

    const handleTrackInputChange = (event) => {
        const query = event.target.value;
        console.log(query);
        setSelectedTrack({ name: query, id: 0 });
        setMp3url('');
        setPlayingIndex(-1);

        searchTracks(query);
    };

    const searchTracks = (query) => {
        if (query.trim() === '') {
            setTracks([]);
            return;
        }
        const url = serverURL + '/track/search/' + query.replaceAll(' ', '+');
        fetch(url)
            .then((res) => res.json())
            .then((data) => setTracks(data || []))
            .catch((err) => console.error(err));
    };

    const selectTrack = (track) => {
        setSelectedTrack({ name: track.name, id: track.id });
        setTracks([]);
        setPlayingIndex(-1);
        setMp3url('');
    };

    const play = (url, index) => {
        if (playingIndex === index) {
            setPlayingIndex(-1);
            setMp3url('');
        } else {
            setPlayingIndex(index);
            setMp3url(url);
        }
    };

    const submit = () => {
        const body = {
            movieID: selectedMovie.id,
            trackID: selectedTrack.id,
        };
        fetch(serverURL + '/match', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        })
            .then((res) => {
                if (!res.ok) throw new Error(res.status);
                setSelectedMovie({ id: 0, title: '' });
                setSelectedTrack({ id: '', name: '' });
                setRedirect(true);
            })
            .catch((err) => console.error(err));
    };

    const abort = () => {
        setRedirect(true);
    };

    if (redirect) {
        return <Redirect to="/admin" />;
    }

    return (
        <div className="new-match">
            <Sound
                url={mp3url}
                playStatus={playingIndex >= 0 ? 'PLAYING' : 'STOPPED'}
            />
            <h1>Add new Match</h1>
            <div className="new-form">
                {/* Moive input */}
                <div className="holder">
                    <input
                        id="movie"
                        placeholder="Movie"
                        onChange={handleMovieInputChange}
                        className="movie-search"
                        value={selectedMovie.title}
                    />
                    <div
                        className={
                            'result-list movie-result' +
                            (movies.length === 0 ? ' hidden' : '')
                        }
                    >
                        {movies.map((movie) => (
                            <div className="result" key={movie.id}>
                                <Movie movie={movie} />
                                <Button onClick={() => selectMovie(movie)}>
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
                        onChange={handleTrackInputChange}
                        className="track-search"
                        value={selectedTrack.name}
                    />
                    <div
                        className={
                            'result-list track-result' +
                            (tracks.length === 0 ? ' hidden' : '')
                        }
                    >
                        {tracks.map((track, index) => (
                            <div className="result" key={track.id}>
                                <Track
                                    track={track}
                                    play={() => play(track.previewURL, index)}
                                    playing={playingIndex === index}
                                />
                                <Button onClick={() => selectTrack(track)}>
                                    <FaCheck />
                                </Button>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <Button variant="primary" className="submit-btn" onClick={submit}>
                <FaCheck /> Submit
            </Button>{' '}
            <Button variant="danger" className="submit-btn" onClick={abort}>
                <FaTimes /> Abort
            </Button>
        </div>
    );
}
