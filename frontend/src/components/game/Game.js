import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Sound from 'react-sound';

import MovieSelection from './MovieSelection';
import LoadingSpinner from '../LoadingSpinner';

import './Game.css';

export default class Game extends Component {
    constructor(props) {
        super(props);

        this.serverURL =
            'http://' +
            process.env.REACT_APP_SERVER_HOST +
            ':' +
            process.env.REACT_APP_SERVER_PORT;

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
            error: false
        };
    }

    componentDidMount() {
        fetch(this.serverURL + '/game')
            .then((res) =>{
                if(!res.ok) {
                    this.setState({error: true, loading: false})
                    throw new Error(res.status + " " + res.statusText);
                }

                return res.json()
            })
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
        if (this.state.loading) {
            return <LoadingSpinner />;
        }

        if (this.state.redirect) {
          return <Redirect
                to={{
                    pathname: '/results',
                    state: { score: this.state.score },
                }}
            />;
        }

        if (this.state.error) {
            return (
                <div className="game">
                    <p>
                        There are not enough matches stored. Go to the <a href="/admin">Admin-Page</a> and create some!
                    </p>
                </div>
            )
        }

        return (
            <div className="game">
                <Sound
                    url={
                        this.state.game.rounds[this.state.round].soundTrack
                            .previewURL
                    }
                    playStatus={this.state.playing ? 'PLAYING' : 'STOPPED'}
                />

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
