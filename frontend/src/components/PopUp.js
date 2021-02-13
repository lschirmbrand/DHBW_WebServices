import React from "react"
import "../styles/PopUp.css"

class PopUp extends React.Component{

    render(){
        console.log("hello")
        return(
            
        <div>
            <div className="modal" id="modal">
                <div className="modal-header">
                    <div className="title">Test</div>
                    <button className="close-button">&times;</button>
                </div>
                <div className="modal-body">
                    Test Test
                </div>
            </div>
            <div className="active" id="overlay"></div>
            </div>
        )

    }

}


export default PopUp