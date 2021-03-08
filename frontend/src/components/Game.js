import React, { Component } from 'react'
import AnswerBox from './AnswerBox';
import Timer from './Timer';

export default class Game extends Component {
    constructor() {
        super();
        this.state = {
            currentQuestion: 0,
        };
    }
    render() {
        setTimeout(30000)
        setTimeout(() => this.render(), 33000)
        // let wave = new Wave();
        // let options = {
        //     type: "shockwave",
        //     stroke: 2,
        //     colors: ["#24292e", "#547ee2"]
        // };
        // wave.fromElement("audio", "wave", options)

        return (
            <div>
                <div id={"HeadlineAndClock"}>
                    <div className={"HeadlineBig"}>
                        <span>Guess the movie!</span>
                    </div>
                    <Timer />
                </div>
                <div className={"OuterDiv"}>
                    <div className={"InfoBox"}>
                        <AnswerBox />
                    </div>
                    <div>{ }</div>
                </div>
                <div>
                    <canvas id="wave">Canvas</canvas>
                    <audio id="audio" src="https://p.scdn.co/mp3-preview/f6ab4a4ae33450c4edb89bb5711e8486d367d257?cid=6b05de1c165548d485b84df3bccc9965%22"></audio>
                </div>
            </div>
        )
    }

}

