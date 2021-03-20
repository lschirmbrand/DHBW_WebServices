import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Button from 'react-bootstrap/Button';

import './Results.css';

export default function Results(props) {
    const score = props.location.state.score;

    return (
        <div className="results">
            <Jumbotron>
                <h1>
                    {(score === 10 ? 'Congratulations, you got ' : 'You got ') +
                        score +
                        ' points!'}
                </h1>
                <p>Press the Button below to play again.</p>
                <a href="/game">
                    <Button variant="primary">Play again</Button>
                </a>
            </Jumbotron>
        </div>
    );
}
