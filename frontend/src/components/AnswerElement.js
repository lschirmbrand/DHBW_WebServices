import React from "react"

class AnswerElement extends React.Component{
    render(){
        return (
            // <div className="AnswerElement">
            <div>
                <button className="AnswerElement">{this.props.name}</button>
            </div>
        )        
    }
}

export default AnswerElement