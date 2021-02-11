import './styles/App.css';
import "./styles/AnswerBoxElement.css"
import React from "react"
import AnswerBox from "./components/AnswerBox"
import Header from "./components/Header"
import Footer from "./components/Footer"
import Navbar from "./components/Navbar"
import Timer from "./components/Timer"
import { render } from '@testing-library/react';

class App extends React.Component{
  render(){
    return(
      <div>
        <Header />
        <Navbar />
        {/* <span className={"HeadlineBig"}>Hier steht eine Überschrift</span> */}
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
