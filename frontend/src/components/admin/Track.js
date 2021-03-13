import React from 'react';
import Button from 'react-bootstrap/Button';
import { FaPlay, FaStop } from 'react-icons/fa';

import './Track.css';

export default function Track({ track, playing, play }) {
    return (
        <div className={'track' + (track.previewURL === 'null' ? ' red' : '')}>
            <img src={track.coverURL} alt={track.name}></img>
            <div className="track-desc">
                <a href={track.url} target="_blank" rel="noreferrer">
                    {track.name}
                </a>
                <span className="track-artist">{track.artistName}</span>
            </div>
            <Button variant="success" onClick={play}>
                {playing ? <FaStop /> : <FaPlay />}
            </Button>
        </div>
    );
}
