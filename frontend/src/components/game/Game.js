import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Sound from 'react-sound';

import MovieSelection from './MovieSelection';
import LoadingSpinner from '../LoadingSpinner';

import './Game.css';

export default function Game() {
    const serverURL =
        'http://' + window._env_.SERVER_HOST + ':' + window._env_.SERVER_PORT;

    const secondsToPlay = 30;
    const intervall = 100;

    const [game, setGame] = useState({});
    const [round, setRound] = useState(0);
    const [loading, setLoading] = useState(true);
    const [score, setScore] = useState(0);
    const [redirect, setRedirect] = useState(false);
    const [timer, setTimer] = useState(null);
    const [timeLeft, setTimeLeft] = useState(secondsToPlay);
    const [playing, setPlaying] = useState(false);
    const [selected, setSelected] = useState(3);
    const [correct, setCorrect] = useState(3);
    const [error, setError] = useState(false);
    const [locked, setLocked] = useState(false);

    const start = () => {
        setPlaying(true);
        const time = setInterval(() => {
            setTimeLeft((old) => {
                return old - intervall / 1000;
            });
        }, intervall);
        setTimer(time);
    };

    useEffect(() => {
        if (timeLeft < 0) {
            clickMovie(3);
        }
    }, [timeLeft]);

    const clickMovie = (index) => {
        if (!locked) {
            setTimeLeft(secondsToPlay);
            clearInterval(timer);
            setSelected(index);
            setCorrect(game.rounds[round].correctIndex);
            setPlaying(false);
            setLocked(true);

            if (index === game.rounds[round].correctIndex) {
                setScore(score + 1);
            }

            setTimeout(() => {
                next();
            }, 1500);
        }
    };

    const next = () => {
        if (round === 9) {
            setRedirect(true);
        } else {
            setRound(round + 1);
            setCorrect(3);
            setSelected(3);
            start();
            setLocked(false);
        }
    };

    useEffect(() => {
        fetch(serverURL + '/game')
            .then((res) => {
                if (!res.ok) {
                    setError(true);
                    setLoading(false);
                    throw new Error(res.status + ' ' + res.statusText);
                }

                return res.json();
            })
            .then((data) => {
                setGame(data);
                setLoading(false);
                setTimer(true);
                start();
            })
            .catch((err) => console.error(err));
    }, []);

    if (loading) {
        return <LoadingSpinner />;
    }

    if (redirect) {
        return (
            <Redirect
                to={{
                    pathname: '/results',
                    state: { score: score },
                }}
            />
        );
    }

    if (error) {
        return (
            <div className="game">
                <p>
                    There are not enough matches stored. Go to the{' '}
                    <a href="/admin">Admin-Page</a> and create some!
                </p>
            </div>
        );
    }

    return (
        <div className="game">
            <Sound
                url={game.rounds[round].soundTrack.previewURL}
                playStatus={playing ? 'PLAYING' : 'STOPPED'}
            />

            <div className="game-info">
                <span className="score">{score}</span>
                <span>Points</span>
                <span>Round {round + 1}/10</span>
                <ProgressBar
                    now={(timeLeft / secondsToPlay) * 100}
                    label={`${timeLeft.toFixed(1)}s`}
                />
            </div>
            <MovieSelection
                movies={game.rounds[round].movies}
                clickMovie={clickMovie}
                correct={correct}
                selected={selected}
            />
        </div>
    );
}
