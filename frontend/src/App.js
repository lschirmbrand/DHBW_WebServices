import './styles/App.css';
import "./styles/AnswerBoxElement.css"
import React, { useEffect } from "react"
import Header from "./components/Header"
import Navbar from "./components/Navbar"
import Game from "./components/Game";
import Admin from './components/admin/Admin'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

class App extends React.Component {
  constructor() {
    super()
  }

  render() {
    return (
      <Router>
        <div className="Screen">
          <Header />
          <Navbar />
          <Switch>
            <Route path="/admin">
              <Admin />
            </Route>
            <Route path="/">
              <Game />
            </Route>
          </Switch>
        </div>
      </Router>
    )
  }
}
export default App;