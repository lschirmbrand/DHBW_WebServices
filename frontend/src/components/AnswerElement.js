import React from "react"

class AnswerElement extends React.Component{
    render(){
        return (
            // <div className="AnswerElement">
            <div>
                <button onClick={() => {/*this.checkAnswer(this.props.key)*/} }className="AnswerElement">{this.props.name}</button>
            </div>
        )        
    }

    checkAnswer(props){
        console.log("Testoutput")
    }
}

export default AnswerElement