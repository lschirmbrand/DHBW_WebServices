import React, { useState } from 'react';
import { Redirect } from 'react-router';

import Button from 'react-bootstrap/Button';
import Jumbotron from 'react-bootstrap/Jumbotron';

import './Home.css';

export default function Home() {
    const [redirect, setRedirect] = useState(false);

    return redirect ? (
        <Redirect to="/game"></Redirect>
    ) : (
        <div className="home">
            <Jumbotron>
                <h1>Welcome to Soundtrack Guesser!</h1>
                <p>
                    The game takes ten rounds. Each round you will be hearing a
                    more ore less common soundtrack of any movie. Now you have
                    to choose the correct Movie, where this soundtrack is taken
                    from.
                </p>
                <Button onClick={() => setRedirect(true)}>Start Game</Button>
            </Jumbotron>
        </div>
    );
}
