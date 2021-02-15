import Wave from "@foobar404/wave"
import './styles/App.css';
import "./styles/AnswerBoxElement.css"
import React from "react"
import AnswerBox from "./components/AnswerBox"
import Header from "./components/Header"
import Footer from "./components/Footer"
import Navbar from "./components/Navbar"
import Timer from "./components/Timer"
import Sidebar from "./components/Sidebar"
import { render } from '@testing-library/react';

class App extends React.Component{
  constructor(){
    super()
    this.state = {
      currentQuestion: 0
    }  
  }
  
  render(){ 
    let wave = new Wave();
    let options = {
      type: "shockwave",
      stroke: 2,
      colors:["#24292e","#547ee2"]
    };
    wave.fromElement("audio", "wave", options)
    
    return(
      <div className = "Screen">
        <script src="https://cdn.jsdelivr.net/gh/PiethonCoder/wave.js/wave.js"></script>
        <Header />
        <Navbar />
        <div id={"HeadlineAndClock"}>
          <div className={"HeadlineBig"}>
            <span>Guess the movie!</span>
          </div>        
          <Timer onComplete={() => this.timeOver()}/> 
        </div>
          <div className={"OuterDiv"}>
            <div className={"InfoBox"}>
              <AnswerBox />               
            </div>
          </div>
          <div>
            <canvas id="wave">Canvas</canvas>
            <audio id="audio" src="https://p.scdn.co/mp3-preview/f6ab4a4ae33450c4edb89bb5711e8486d367d257?cid=6b05de1c165548d485b84df3bccc9965%22"></audio>
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
