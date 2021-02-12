import React from "react"

class AnswerElement extends React.Component{
    render(){
        return (
            // <div className="AnswerElement">
            <div className={"ElementCell"}>
                <button onClick={() => {/*this.checkAnswer(this.props.key)*/} } className="AnswerElement"></button>
                <p className="ElementPar">{this.props.name}</p>
            </div>
        )        
    }

    checkAnswer(props){
        console.log("Testoutput")
    }
}

export default AnswerElement