import React from "react"
import TableItems from "./TableItems"
import PopUpEdit from "./PopUpEdit"
import "../styles/Admin.css"


class Admin extends React.Component {
    constructor() {
        super()
        this.state = {
            moviesData: [],
            display: false
        }
    }

    componentDidMount() {
        fetch('http://localhost:8081/match')
            .then(response => response.json())
            .then(data => { this.setState({ moviesData: data }) })
    }

    render() {

        const moviesData = this.state.moviesData
        const movies = moviesData.map((d) => <TableItems object={d} key={d.id} />)

        return (
            <div>
                <div id="table">
                    <table className="content-table">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>title</th>
                                <th>genres</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            { }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default Admin