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
    // var wave = new Wave();
    // var options = {
    //   type: "shockwave",
    //   stroke: 2,
    //   colors:["#24292e","#547ee2"]
    // };
    // wave.fromElement("audio", "wave", options)
    // wave.playStream()
  }
  
  render(){    
    return(
      <div>
        {/* <script src="https://cdn.jsdelivr.net/gh/PiethonCoder/wave.js/wave.js"></script>*/}
        <Header />
        <Navbar />
        <div className={"HeadlineBig"}>
          <span>Guess the movie!</span>
        </div>        
        <Timer onComplete={() => this.timeOver()}/> 
        <div className={"OuterDiv"}>
          <div className={"InfoBox"}>
            <AnswerBox />               
          </div>
          {/* <Sidebar results={this.resultHis}/> */}
        </div>
        {/*<canvas id="wave">Canvas</canvas>
          <audio id="audio" controls>
          <source src="http://localhost:3000/"></source>
        </audio> */}
        {/*<Footer />*/}
      </div>     
    )
  }
  timeOver(){
    console.log("d")
  }
}
export default App;
