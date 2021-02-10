import React from "react"
import AnswerElement from "./AnswerElement"

class AnswerBox extends React.Component{
    render(){
        return(
            <div className={"AnswerBox"}>
                <AnswerElement />
                <AnswerElement />
                <AnswerElement />
                <AnswerElement />
                <button>Ok</button>
            </div>
        )
    }
}

export default AnswerBox
