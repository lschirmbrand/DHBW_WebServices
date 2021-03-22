import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import Home from './components/Home';
import Admin from './components/admin/Admin';
import NewMatchForm from './components/admin/NewMatchForm';
import Game from './components/game/Game';
import Header from './components/Header';
import Results from './components/Results';

import './App.css';

export default function App() {
    return (
        <Router>
            <div className="app">
                <Header />
                <Switch>
                    <Route path="/admin/new">
                        <NewMatchForm />
                    </Route>
                    <Route path="/admin">
                        <Admin />
                    </Route>
                    <Route
                        path="/results"
                        render={(props) => <Results {...props} />}
                    />
                    <Route path="/game">
                        <Game />
                    </Route>
                    <Route path="/">
                        <Home />
                    </Route>
                </Switch>
            </div>
        </Router>
    );
}
