import React, { useEffect, useRef, useState } from 'react';
import './Admin.css';

import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

import { Redirect } from 'react-router-dom';
import { FaFileExport, FaFileImport, FaPlus, FaTrash } from 'react-icons/fa';
import Sound from 'react-sound';

import Movie from './Movie';
import Track from './Track';
import LoadingSpinner from '../LoadingSpinner';

export default function Admin() {
    const serverURL =
        'http://' + window._env_.SERVER_HOST + ':' + window._env_.SERVER_PORT;

    const [loading, setLoading] = useState(true);
    const [matches, setMatches] = useState([]);
    const [redirect, setRedirect] = useState(false);
    const [fileDownloadUrl, setFileDownloadUrl] = useState('');
    const [playingIndex, setPlayingIndex] = useState(-1);
    const [mp3url, setMp3url] = useState('');

    const upload = useRef(null);
    const download = useRef(null);

    useEffect(() => {
        fetch(serverURL + '/match')
            .then((response) => response.json())
            .then((data) => {
                setMatches(data);
                setLoading(false);
            });
    }, []);

    const removeClick = (matchID) => {
        fetch(serverURL + '/match/' + matchID, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
        }).then(() =>
            setMatches(matches.filter((match) => match.id !== matchID))
        );
    };

    const clickExport = () => {
        const data = matches.map((match) => ({
            trackID: match.track.id,
            movieID: match.movie.id,
        }));

        const blob = new Blob([JSON.stringify({ matches: data })]);

        const url = URL.createObjectURL(blob);

        setFileDownloadUrl(url);
    };

    useEffect(() => {
        if (fileDownloadUrl !== '') {
            download.current.click();
            URL.revokeObjectURL(fileDownloadUrl);
            setFileDownloadUrl('');
        }
    }, []);

    const importFile = (file) => {
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            const content = fileReader.result;
            console.log(content);
            JSON.parse(content).matches.forEach((match) =>
                fetch(serverURL + '/match', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(match),
                })
                    .then((res) => {
                        if (!res.ok) throw new Error(res.status);
                        return res.json();
                    })
                    .then((data) => {
                        console.log(matches);
                        setMatches((old) => [...old, data]);
                    })
                    .catch((err) => console.error(err))
            );
        };
        fileReader.readAsText(file);
    };

    const play = (url, index) => {
        if (playingIndex === index) {
            setPlayingIndex(-1);
            setMp3url('');
        } else {
            setPlayingIndex(index);
            setMp3url(url);
        }
    };

    if (loading) {
        return <LoadingSpinner />;
    }

    if (redirect) {
        return <Redirect to="/admin/new" />;
    }

    return (
        <div className="admin">
            <Sound
                url={mp3url}
                playStatus={playingIndex >= 0 ? 'PLAYING' : 'STOPPED'}
            />
            <div id="table">
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Movie</th>
                            <th>Soundtrack</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colSpan="3">
                                <Button
                                    onClick={() => setRedirect(true)}
                                    variant="success"
                                >
                                    <FaPlus /> Add
                                </Button>{' '}
                                <Button
                                    onClick={clickExport}
                                    variant="secondary"
                                >
                                    <FaFileExport /> Export
                                </Button>{' '}
                                <Button
                                    onClick={() => upload.current.click()}
                                    variant="secondary"
                                >
                                    <FaFileImport /> Import
                                </Button>
                                <input
                                    type="file"
                                    id="importFile"
                                    style={{ display: 'none' }}
                                    ref={upload}
                                    onChange={(e) =>
                                        importFile(e.target.files[0])
                                    }
                                />
                            </td>
                        </tr>
                        {matches.map((match, index) => (
                            <tr key={match.id}>
                                <td>
                                    <Movie movie={match.movie} />
                                </td>
                                <td>
                                    <Track
                                        track={match.track}
                                        play={() =>
                                            play(match.track.previewURL, index)
                                        }
                                        playing={playingIndex === index}
                                    />
                                </td>
                                <td className="delete-cell">
                                    <Button
                                        className="delete-btn"
                                        variant="danger"
                                        onClick={() => removeClick(match.id)}
                                    >
                                        <FaTrash />
                                    </Button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
            <a
                style={{ display: 'none' }}
                download="matches.json"
                href={fileDownloadUrl}
                ref={download}
            >
                download it
            </a>
        </div>
    );
}
