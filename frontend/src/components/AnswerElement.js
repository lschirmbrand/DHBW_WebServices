import React from "react"

class AnswerElement extends React.Component{
    constructor(){
        super()
        this.state ={
            
        }
    }


    render(){
        console.log(this.props.thumbnail)
        return (
            // <div className="AnswerElement">
            <div className={"ElementCell"}>
                <button onClick={() => {this.checkAnswer(this.props.key)} } className="AnswerElement">
                    <img className="AnswerElement" src={this.props.object.posterURL}></img>
                </button>
                <p className="ElementPar">{this.props.object.title}</p>
            </div>
        )        
    }

    checkAnswer(props){
        console.log(props)
    }
}

export default AnswerElement