import React from 'react'

function Movie({ movie }) {
    return (
        <a className="movie" href={"https://www.themoviedb.org/movie/" + movie.id} target="_blank">
            {movie.posterURL != '' && <img src={movie.posterURL}></img>}
            <span>{movie.title}</span>
        </a >
    )
}

export default Movie

