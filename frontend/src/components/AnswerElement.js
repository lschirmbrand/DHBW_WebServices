import React from "react"

class AnswerElement extends React.Component{
    render(){
        let title = this.props.object.title
        return (
            // <div className="AnswerElement">
            <div className={"ElementCell"}>
                <button onClick={() => {this.checkAnswer(this.key)} } className="AnswerElement"></button>
                <p className="ElementPar">{title}</p>
            </div>
        )        
    }

    checkAnswer(props){
        console.log(props)
    }
}

export default AnswerElement