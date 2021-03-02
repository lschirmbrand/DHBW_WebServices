import React from "react"
import "../styles/PopUp.css"
import App from "../App"


class PopUp extends React.Component {

    constructor() {
        super()
        this.state = {
            display: true
        }
    }

    render(props) {
        if (this.state.display == true) {
                return (
                    <div>
                        <div className={"modal"} id={"modal"}>
                            <div className={"modal-header"}>
                                <div className={"title"}>{this.props.answer? "Richtig" : "Falsch"}</div>
                                <button onClick={() => this.closePopUp()} className={"close-button"}>&times;</button>
                            </div>
                            <div className={"modal-body"}>{this.props.answer? "Ja, richtig!" : "Nein, das war leider nicht der richtige Film."}</div>
                        </div>
                        <div id={"overlay"}></div>
                    </div>
                )
            }
        else {
            return null;
        }


    }
    closePopUp() {
        console.log("Close-Button")
        this.setState({
            display: false,
        })
    }
}


export default PopUp