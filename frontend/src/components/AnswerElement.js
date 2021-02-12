import React from "react"

class AnswerElement extends React.Component{
    constructor(){
        super()
        this.state ={
            
        }
    }

    render(props){
        return (            
            <div className={"ElementCell"}>
                    <img onClick={() => {this.checkAnswer()} } className="AnswerElement" src={this.props.object.posterURL}></img>
                <p className="ElementPar">{this.props.object.title}</p>
            </div>
        )        
    }

    checkAnswer(props){
        console.log("Es wurde das Element mit der ID "+this.props.id+" geklickt")
    }
}

export default AnswerElement