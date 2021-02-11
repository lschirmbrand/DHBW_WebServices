import React from "react"

class Sidebar extends React.Component{

    numberQuestions = 10;
    spans = []

    render(){
        return(
            <div className={"Sidebar"}>
                <span>Spielübersicht:</span>
            </div>            
        )
    }
}

export default Sidebar