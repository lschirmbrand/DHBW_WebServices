import React from 'react';
import './MovieSelection.css';

export default function MovieSelection(props) {
    const classes = [0, 1, 2].map((index) => {
        if (props.correct === index) return 'correct';
        if (props.selected === index) return 'wrong';
        else return '';
    });

    return (
        <div className="movies">
            {props.movies.map((movie, index) => (
                <div className="movie-select">
                    <img
                        className={'poster ' + classes[index]}
                        src={movie.posterURL}
                        alt={movie.title}
                        onClick={() => props.clickMovie(index)}
                    ></img>
                    <div>{movie.title}</div>
                </div>
            ))}
        </div>
    );
}
