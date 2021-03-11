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
            timer: null,
            timeLeft: 300,
            playing: false,
            selected: 3,
            correct: 3,
        };
    }

    componentDidMount() {
        fetch('http://localhost:8081/game')
            .then((res) => res.json())
            .then((data) => {
                this.setState({ game: data, loading: false, timer: true });
                this.start();
            })
            .catch((err) => console.error(err));
    }

    start = () => {
        this.setState({ playing: true });
        const timer = setInterval(() => {
            this.setState({ timeLeft: this.state.timeLeft - 1 });
            if (this.state.timeLeft === 0) {
                this.clickMovie(3);
            }
        }, 100);
        this.setState({ timer });
    };

    clickMovie = (index) => {
        const { game, round, score } = this.state;

        clearInterval(this.state.timer);
        this.setState({
            selected: index,
            correct: game.rounds[round].correctIndex,
            playing: false,
            timeLeft: 300,
        });

        if (index === game.rounds[round].correctIndex) {
            this.setState({ score: score + 1 });
        } else {
            this.setState({ selected: index });
        }

        setTimeout(() => {
            this.next();
        }, 1500);
    };

    next = () => {
        const { round } = this.state;

        if (round === 9) {
            this.setState({ redirect: true });
        } else {
            this.setState({
                round: round + 1,
                secondsLeft: 30,
                correct: 3,
                selected: 3,
            });
            this.start();
        }
    };

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
                    playStatus={this.state.playing ? 'PLAYING' : 'STOPPED'}
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
                    <span className="score">{this.state.score}</span>
                    <span>Points</span>
                    <span>Round {this.state.round + 1}/10</span>
                    <ProgressBar
                        now={(this.state.timeLeft * 100) / 300}
                        label={`${(this.state.timeLeft / 10).toFixed(1)}s`}
                    />
                </div>
                <MovieSelection
                    movies={this.state.game.rounds[this.state.round].movies}
                    clickMovie={this.clickMovie}
                    correct={this.state.correct}
                    selected={this.state.selected}
                />
            </div>
        );
    }
}
