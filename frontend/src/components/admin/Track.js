import React from 'react';

export default function Track({ track }) {
    return (
        <a
            className={
                'track' + (track.previewURL == 'null' ? ' no-preview' : '')
            }
            href={track.spotifyURL}
            target="_blank"
            rel="noreferrer"
        >
            <img src={track.coverURL}></img>
            <div className="track-desc">
                <span className="track-name">{track.name}</span>
                <span className="track-artist">{track.artistName}</span>
            </div>
        </a>
    );
}
