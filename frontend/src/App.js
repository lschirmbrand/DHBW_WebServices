import './styles/App.css';
import React from "react"

import AnswerBox from "./components/AnswerBox"
import Header from "./components/Header"
import Footer from "./components/Footer"
import Navbar from "./components/Navbar"

class App extends React.Component{
  render(){
    return(
      <div>
        <Header />
        <Navbar />
        <h1>Hier steht eine Überschrift</h1>
        <AnswerBox />
        <Footer />
      </div>     
    )
  }
}

export default App;
