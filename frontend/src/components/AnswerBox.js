import React from "react"
import AnswerElement from "./AnswerElement"

class AnswerBox extends React.Component{
    constructor(){
        super()
        this.state = {
            correctAnswer: Math.floor(Math.random() * Math.floor(4)) + 1,
            movieData : []
        }
    }

    componentDidMount(){
        fetch('http://localhost:8081/movies')
            .then(response => response.json())
            .then(data => {this.setState({movieData: data})})

    }

    render(){   
        
        const listItems = this.state.movieData.map((d) => <AnswerElement object={d}/>)
        
        let elem = [{"name":"Star Wars"},{"name":"Herr der Ringe"},{"name":"Harry Potter"}]
        let myArray = []
        
        for(let index = 0; index < elem.length; index++){
            let worked = false;
            while(!worked){
                let temp = Math.floor(Math.random() * Math.floor(4))               
                if(myArray[temp] == null){
                    myArray[temp] = elem[index]
                    worked = true
                }
            }
            worked = false
        }
        
        

        return(
            <div className={"AnswerBox"}>
                {listItems}
                {/* {this.state.movieData */}
            </div>
        )
    }
}

export default AnswerBox
