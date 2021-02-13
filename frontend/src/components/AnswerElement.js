import React from "react"
import PopUp from "./PopUp.js"


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

    checkAnswer(){
        console.log("Es wurde das Element mit der ID "+this.props.id+" geklickt.")
        if(this.props.id == this.props.rightObject.id){
            <PopUp/>
            console.log("ok")
        }
        else{
            console.log("no")
        }

        
    }
}

export default AnswerElement