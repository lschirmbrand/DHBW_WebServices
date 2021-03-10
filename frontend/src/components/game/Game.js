import React, { Component } from 'react';

import Spinner from 'react-bootstrap/Spinner';
import ProgressBar from 'react-bootstrap/ProgressBar';
import { Redirect } from 'react-router-dom';
import MovieSelection from './MovieSelection';
import Sound from 'react-sound';

import './Game.css';

export default class Game extends Component {
    constructor(props) {
        super(props);
        this.state = {
            round: 0,
            game: {},
            loading: true,
            score: 0,
            redirect: false,
            timer: false,
            secondsLeft: 30,
            play: false,
        };
        this.audio = new Audio(
            'https://open.spotify.com/track/1p791U7Bx5PmvCwucN4PQN'
        );
    }

    componentDidMount() {
        fetch('http://localhost:8081/game')
            .then((res) => res.json())
            .then((data) => {
                this.setState({ game: data, loading: false, timer: true });
                this.play();
            })
            .catch((err) => console.error(err));

        setInterval(() => {
            if (this.state.timer)
                this.setState({ secondsLeft: this.state.secondsLeft - 1 });
            if (this.state.secondsLeft === 0) {
                // this.clickMovie(3);
            }
        }, 1000);
    }

    clickMovie = (index) => {
        const { game, round, score } = this.state;

        if (index === game.rounds[round].correctIndex) {
            this.setState({ score: score + 1 });
        }

        if (round === 9) {
            this.setState({ redirect: true });
        } else {
            this.setState({ round: round + 1, secondsLeft: 30 });
        }
    };

    play() {
        // this.audio = new Audio(
        //     this.state.game.rounds[this.state.round].soundtrack.previewURL
        // );
        this.setState({ play: true });
        this.audio.play();
    }

    render() {
        return this.state.loading ? (
            <Spinner animation="border" />
        ) : (
            <div className="game">
                <Sound
                    url={
                        this.state.game.rounds[this.state.round].soundTrack
                            .previewURL
                    }
                    playStatus={Sound.status.PLAYING}
                />

                {this.state.redirect && (
                    <Redirect
                        to={{
                            pathname: '/results',
                            state: { score: this.state.score },
                        }}
                    />
                )}

                <div className="game-info">
                    <span>Round {this.state.round}/10</span>
                    <span>Score: {this.state.score}</span>
                    <ProgressBar
                        now={(this.state.secondsLeft * 100) / 30}
                        label={`${this.state.secondsLeft}s`}
                    />
                </div>
                <MovieSelection
                    movies={this.state.game.rounds[this.state.round].movies}
                    clickMovie={this.clickMovie}
                />
            </div>
        );
    }
}
