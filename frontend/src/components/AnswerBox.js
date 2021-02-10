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
            </div>
        )
    }
}

export default AnswerBox
