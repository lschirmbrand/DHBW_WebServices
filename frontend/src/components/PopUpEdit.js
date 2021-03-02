import React from "react"
import "../styles/PopUp.css"


class PopUpEdit extends React.Component {

    constructor() {
        super()
        this.state = {
            display: true
        }
    }

    render(props) {
        if (this.state.display == true) {
                return (
                    <div id="Edit">
                        <div className={"modal"} id={"modal"}>
                            <div className={"modal-header"}>
                                <div className={"title"}>Edit</div>
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


export default PopUpEdit