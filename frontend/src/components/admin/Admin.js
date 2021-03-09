import React from 'react';
import '../../styles/Admin.css';
import Movie from './Movie';
import Track from './Track';

import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

import { Redirect } from 'react-router-dom';

class Admin extends React.Component {
    constructor({ matches }) {
        super();
        this.state = {
            matches: matches || [],
            redirect: false,
        };
    }

    componentDidMount() {
        if (this.props.location.state) {
            this.setState(this.props.location.state);
        } else {
            fetch('http://localhost:8081/match')
                .then((response) => response.json())
                .then((data) => {
                    this.setState({ matches: data });
                });
        }
    }

    setRedirect = () => this.setState({ redirect: true });

    render() {
        return (
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
                                </tr>
                            ))}
                            <tr>
                                <td colSpan="2">
                                    <Button onClick={this.setRedirect}>
                                        Add
                                    </Button>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </div>
            </div>
        );
    }
}

export default Admin;
