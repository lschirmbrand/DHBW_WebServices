import React from 'react';
import './Admin.css';
import Movie from './Movie';
import Track from './Track';

import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

import { Redirect } from 'react-router-dom';
import { FaFileExport, FaFileImport, FaPlus, FaTrash } from 'react-icons/fa';
import Spinner from 'react-bootstrap/esm/Spinner';

class Admin extends React.Component {
    constructor({ matches }) {
        super();
        this.state = {
            loading: true,
            matches: matches || [],
            redirect: false,
            fileDownloadUrl: '',
        };
        this.upload = React.createRef();
    }

    componentDidMount() {
        if (this.props.location.state) {
            this.setState(this.props.location.state);
        } else {
            fetch('http://localhost:8081/match')
                .then((response) => response.json())
                .then((data) =>
                    this.setState({ matches: data, loading: false })
                );
        }
    }

    setRedirect = () => this.setState({ redirect: true });

    removeClick = (matchID) => {
        fetch('http://localhost:8081/match/' + matchID, {
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
            console.log(this.state);
            this.setState({ fileDownloadUrl: '' });
        });
    };

    clickImport = () => {};
    importFile = (file) => {
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            const content = fileReader.result;
            JSON.parse(content).matches.forEach((match) =>
                fetch('http://localhost:8081/match', {
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
                            {this.state.matches.map((match) => (
                                <tr key={match.id}>
                                    <td>
                                        <Movie movie={match.movie} />
                                    </td>
                                    <td>
                                        <Track track={match.track} />
                                    </td>
                                    <td>
                                        <Button
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
