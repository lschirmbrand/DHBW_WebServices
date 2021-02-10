import React from "react"

class AnswerElement extends React.Component{
    render(){
        return (
            <div className="AnswerElement">
                <input type= "checkbox"></input>{this.props.name}
            </div>
        )        
    }
}

export default AnswerElement