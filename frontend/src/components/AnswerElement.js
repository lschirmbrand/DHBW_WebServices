import React from "react"

class AnswerElement extends React.Component{
    render(){
        return (
            <div className="AnswerElement">
                <input type= "checkbox"></input>
                <p>Antwortmöglichkeit</p>
            </div>
        )        
    }
}

export default AnswerElement