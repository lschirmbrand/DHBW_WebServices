import './styles/App.css';
import './styles/AnswerBoxElement.css';
import React from 'react';
import Header from './components/Header';
import Navbar from './components/Navbar';
import Game from './components/Game';
import Admin from './components/admin/Admin';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import NewMatchForm from './components/admin/NewMatchForm';

class App extends React.Component {
    render() {
        return (
            <Router>
                <div className="Screen">
                    <Header />
                    <Navbar />
                    <Switch>
                        <Route
                            path="/admin/new"
                            render={(props) => <NewMatchForm {...props} />}
                        />
                        <Route
                            path="/admin"
                            render={(props) => <Admin {...props} />}
                        />
                        <Route path="/">
                            <Game />
                        </Route>
                    </Switch>
                </div>
            </Router>
        );
    }
}
export default App;
