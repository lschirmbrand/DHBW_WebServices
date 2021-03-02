import React from "react"
import PopUp from "./PopUpGuess"



class AnswerElement extends React.Component{
    constructor(){
        super()
        this.state ={
            display:false
        }
    }

    render(props){
        
        return (            
            <div className={"ElementCell"}>
                    <img onClick={() => {this.checkAnswer()}} className={"AnswerElement"} src={this.props.object.posterURL}></img>
                <p className={"ElementPar"}>{this.props.object.title}</p>
                <div>{this.state.display ? <PopUp answer={this.state.answer} display={this.state.display}/> : null}</div>
            </div>
        )        
    }

    checkAnswer(){
        console.log("Es wurde das Element mit der ID "+this.props.id+" geklickt.\n Richtig ist das Element mit der ID "+this.props.rightObject.id+".")
        if(this.props.id == this.props.rightObject.id){
            this.setState({
                display: true,
                answer: true
            })
        }
        else{
            this.setState({
                display: true,
                answer: false
            })
        }
    }
}

export default AnswerElement