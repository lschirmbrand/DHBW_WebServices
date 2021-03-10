import React from 'react';
import './Track.css';

export default function Track({ track }) {
    return (
        <div className={'track' + (track.previewURL === 'null' ? ' red' : '')}>
            <img src={track.coverURL} alt={track.name}></img>
            <div className="track-desc">
                <a href={track.spotifyURL} target="_blank" rel="noreferrer">
                    {track.name}
                </a>
                <span className="track-artist">{track.artistName}</span>
            </div>
        </div>
    );
}
