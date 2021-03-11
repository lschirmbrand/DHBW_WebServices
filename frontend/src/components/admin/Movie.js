import React from 'react';

import './Movie.css';

function Movie({ movie }) {
    return (
        <div className={'movie'}>
            {movie.posterURL !== '' && (
                <img src={movie.posterURL} alt={movie.title}></img>
            )}
            <a
                href={'https://www.themoviedb.org/movie/' + movie.id}
                target="_blank"
                rel="noreferrer"
            >
                {movie.title}
            </a>
        </div>
    );
}

export default Movie;
