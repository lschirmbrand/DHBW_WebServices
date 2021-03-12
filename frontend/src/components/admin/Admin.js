import React from 'react';
import './Admin.css';

import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

import { Redirect } from 'react-router-dom';
import { FaFileExport, FaFileImport, FaPlus, FaTrash } from 'react-icons/fa';
import Spinner from 'react-bootstrap/esm/Spinner';
import Sound from 'react-sound';

import Movie from './Movie';
import Track from './Track';

class Admin extends React.Component {
    constructor({ matches }) {
        super();
        this.serverURL = 'http://' + process.env.REACT_APP_SERVER_HOST + ':' + process.env.REACT_APP_SERVER_PORT;
        this.state = {
            loading: true,
            matches: matches || [],
            redirect: false,
            fileDownloadUrl: '',
            playingIndex: -1,
            mp3url: '',
        };
        this.upload = React.createRef();
    }

    componentDidMount() {
        if (this.props.location.state) {
            this.setState(this.props.location.state);
        } else {
            fetch(this.serverURL + '/match')
                .then((response) => response.json())
                .then((data) => {
                    this.setState({ matches: data, loading: false });
                });
        }
    }

    setRedirect = () => this.setState({ redirect: true });

    removeClick = (matchID) => {
        fetch(this.serverURL + '/match/' + matchID, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
        }).then((res) =>
            this.setState({
                matches: this.state.matches.filter(
                    (match) => match.id !== matchID
                ),
            })
        );
    };

    clickExport = () => {
        const matches = this.state.matches.map((match) => ({
            spotifyID: match.track.id,
            tmdbID: match.movie.id,
        }));

        const blob = new Blob([JSON.stringify({ matches })]);
        const fileDownloadUrl = URL.createObjectURL(blob);
        this.setState({ fileDownloadUrl: fileDownloadUrl }, () => {
            this.dofileDownload.click();
            URL.revokeObjectURL(fileDownloadUrl);
            this.setState({ fileDownloadUrl: '' });
        });
    };

    clickImport = () => {};
    importFile = (file) => {
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            const content = fileReader.result;
            JSON.parse(content).matches.forEach((match) =>
                fetch(this.serverURL + '/match', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(match),
                })
                    .then((res) => res.json())
                    .then((data) =>
                        this.setState({
                            matches: [...this.state.matches, data],
                        })
                    )
            );
        };
        fileReader.readAsText(file);
    };

    play = (url, index) => {
        if (this.state.playingIndex === index) {
            this.setState({ playingIndex: -1, url: '' });
        } else {
            this.setState({ mp3url: url, playingIndex: index });
        }
    };

    render() {
        return this.state.loading ? (
            <Spinner animation="border" />
        ) : (
            <div className="admin">
                {this.state.redirect && (
                    <Redirect
                        to={{
                            pathname: '/admin/new',
                            state: { matches: this.state.matches },
                        }}
                    />
                )}

                <Sound
                    url={this.state.mp3url}
                    playStatus={
                        this.state.playingIndex >= 0 ? 'PLAYING' : 'STOPPED'
                    }
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
                                        onClick={this.setRedirect}
                                        variant="success"
                                    >
                                        <FaPlus /> Add
                                    </Button>{' '}
                                    <Button
                                        onClick={this.clickExport}
                                        variant="secondary"
                                    >
                                        <FaFileExport /> Export
                                    </Button>{' '}
                                    <Button
                                        onClick={() =>
                                            this.upload.current.click()
                                        }
                                        variant="secondary"
                                    >
                                        <FaFileImport /> Import
                                    </Button>
                                    <input
                                        type="file"
                                        id="importFile"
                                        style={{ display: 'none' }}
                                        ref={this.upload}
                                        onChange={(e) =>
                                            this.importFile(e.target.files[0])
                                        }
                                    />
                                </td>
                            </tr>
                            {this.state.matches.map((match, index) => (
                                <tr key={match.id}>
                                    <td>
                                        <Movie movie={match.movie} />
                                    </td>
                                    <td>
                                        <Track
                                            track={match.track}
                                            play={() =>
                                                this.play(
                                                    match.track.previewURL,
                                                    index
                                                )
                                            }
                                            playing={
                                                this.state.playingIndex ===
                                                index
                                            }
                                        />
                                    </td>
                                    <td className="delete-cell">
                                        <Button
                                            className="delete-btn"
                                            variant="danger"
                                            onClick={(e) =>
                                                this.removeClick(match.id)
                                            }
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
                    href={this.state.fileDownloadUrl}
                    ref={(e) => (this.dofileDownload = e)}
                >
                    download it
                </a>
            </div>
        );
    }
}

export default Admin;
