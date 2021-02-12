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
                    <img onClick={() => {console.log(this.props.id)} } className="AnswerElement" src={this.props.object.posterURL}></img>
                <p className="ElementPar">{this.props.object.title}</p>
            </div>
        )        
    }

    checkAnswer(props){
        console.log(props)
    }
}

export default AnswerElement