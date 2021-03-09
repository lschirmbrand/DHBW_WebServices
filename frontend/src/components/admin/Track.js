import React from 'react';
import './Track.css';

export default function Track({ track }) {
    return (
        <div className={'track'}>
            <img src={track.coverURL} alt={track.name}></img>
            <div className="track-desc">
                <a
                    className="track-name"
                    href={track.spotifyURL}
                    target="_blank"
                    rel="noreferrer"
                >
                    {track.name}
                </a>
                <span className="track-artist">{track.artistName}</span>
            </div>
        </div>
    );
}
