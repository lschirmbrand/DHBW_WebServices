import React from 'react';
import Header from './components/Header';
import Game from './components/game/Game';
import Admin from './components/admin/Admin';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import NewMatchForm from './components/admin/NewMatchForm';
import Results from './components/Results';

import './App.css';
import Home from './Home';
class App extends React.Component {
    render() {
        return (
            <Router>
                <div className="app">
                    <Header />
                    <Switch>
                        <Route
                            path="/admin/new"
                            render={(props) => <NewMatchForm {...props} />}
                        />
                        <Route
                            path="/admin"
                            render={(props) => <Admin {...props} />}
                        />
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
}
export default App;
