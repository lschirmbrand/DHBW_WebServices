import './styles/App.css';
import "./styles/AnswerBoxElement.css"
import React from "react"
import AnswerBox from "./components/AnswerBox"
import Header from "./components/Header"
import Footer from "./components/Footer"
import Navbar from "./components/Navbar"
import Timer from "./components/Timer"

class App extends React.Component{
  render(){
    return(
      <div>
        <Header />
        <Navbar />
        <h1>Hier steht eine Überschrift</h1>
        <div className={"InfoBox"}>
          <AnswerBox />
          <Timer onComplete={() => this.timeOver()}/>
        </div>
        <Footer />
      </div>     
    )
  }
  timeOver(){
    console.log("d")
  }
}

export default App;
