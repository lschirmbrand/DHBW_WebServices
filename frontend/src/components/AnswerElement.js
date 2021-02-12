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
                <button onClick={() => {console.log(this.props.id)} } className="AnswerElement">
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