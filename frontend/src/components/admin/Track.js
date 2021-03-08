import React from 'react'

export default function Track({ track }) {
    return (
        <a className="track" href={track.spotifyURL} target="_blank">
            <img src={track.coverURL}></img>
            <div className="track-desc">
                <span className="track-name">{track.name}</span>
                <span className="track-artist">{track.artistName}</span>
            </div>
        </a>
    )
}
