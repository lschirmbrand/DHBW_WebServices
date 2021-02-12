import React from "react"
import AnswerElement from "./AnswerElement"

class AnswerBox extends React.Component{
    constructor(){
        super()
        this.state = {
            correctAnswer: Math.floor(Math.random() * Math.floor(3)),
            movieData : []
        }
    }

    componentDidMount(){
        fetch('http://localhost:8081/movies')
            .then(response => response.json())
            .then(data => {this.setState({movieData: data})})
    }

    render(){
        let myArray = []
        

        for(let index = 0; index < this.state.movieData.length; index++){
            let worked = false;
            while(!worked){
                let temp = Math.floor(Math.random() * Math.floor(4))               
                if(myArray[temp] == null){
                    myArray[temp] = this.state.movieData[index]
                    worked = true
                }
            }
            worked = false
        }


        const rightObject = this.state.movieData[this.state.correctAnswer]
        const listItems = myArray.map((d) => <AnswerElement object={d} id={d.id} key={d.id} rightObject={rightObject}/>)       
        

        return(
            <div className={"AnswerBox"}>
                {listItems}
            </div>
        )
    }
}

export default AnswerBox
