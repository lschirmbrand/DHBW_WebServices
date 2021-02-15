import React from "react"
import "../styles/PopUp.css"


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
                        <div className={"modal active"} id={"modal"}>
                            <div className={"modal-header"}>
                                <div className={"title"}>{this.props.answer? "True" : "False"}</div>
                                <button onClick={() => this.closePopUp()} className={"close-button"}>&times;</button>
                            </div>
                            <div className={"modal-body"}>{this.props.answer? "True True True" : "False False False"}</div>
                        </div>
                        <div className={"active"} id={"overlay-active"}></div>
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
            display: false
        })
    }
}


export default PopUp