import React from "react"

class AnswerElement extends React.Component{
    constructor(){
        super()
        this.state ={
            thumbnailPre: "https://image.tmdb.org/t/p/w1280"
        }
    }
    render(){
        console.log(this.props.thumbnail)
        return (
            // <div className="AnswerElement">
            <div className={"ElementCell"}>
                <button onClick={() => {this.checkAnswer(this.props.key)} } className="AnswerElement">
                    <img className="AnswerElement" src={this.props.thumbnail}></img>
                </button>
                <p className="ElementPar">{this.props.name}</p>
            </div>
        )        
    }

    checkAnswer(props){
        console.log(props)
    }
}

export default AnswerElement