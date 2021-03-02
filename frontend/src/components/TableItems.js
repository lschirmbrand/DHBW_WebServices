import React from "react"
import PopUpEdit from "./PopUpEdit"

class TableItems extends React.Component {
    constructor() {
        super()
        this.state = {
            display: false
        }
    }

    render(props) {
        const genres = this.props.object.genres
        if(this.state.display){
            return(<PopUpEdit/>)
        }
        return (
            <tr>
                <td onClick={() => this.open()}>{this.props.object.id}</td>
                <td>{this.props.object.title}</td>
                <td>{genres.join(", ")}</td>
            </tr>
        )
    }
    open() {
        console.log("Open")
        this.setState({ display: true })
    }
}

export default TableItems