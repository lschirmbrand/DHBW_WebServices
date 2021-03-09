import React from 'react';
import './MovieSelection.css';

export default function MovieSelection(props) {
    return (
        <div className="movies">
            {props.movies.map((movie, index) => (
                <div className="movie-select">
                    <img
                        className="poster"
                        src={movie.posterURL}
                        alt={movie.title}
                        onClick={() => props.clickMovie(index)}
                    ></img>
                    <span>{movie.title}</span>
                </div>
            ))}
        </div>
    );
}
