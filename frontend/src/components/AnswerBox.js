import React from "react"
import AnswerElement from "./AnswerElement"

class AnswerBox extends React.Component{
    constructor(){
        super()
        this.state = {
            correctAnswer: Math.floor(Math.random() * Math.floor(4)) + 1
        }
    }

    render(){        
        let elem = [{"name":"Star Wars"},{"name":"Herr der Ringe"},{"name":"Harry Potter"},{"name":"Iron Sky"}]
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
        
        const listItems = myArray.map((d) => <AnswerElement name={d.name}/>);

        return(
            <div className={"AnswerBox"}>
                {listItems}
            </div>
        )
    }
}

export default AnswerBox
