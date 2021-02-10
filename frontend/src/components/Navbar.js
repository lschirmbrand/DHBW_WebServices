import React from "react"
import "../styles/Navbar.css"
class Navbar extends React.Component{
    render(){
        return(
            <div className={"Navbar"}>
                <a  
                    className={"Navbar"}
                    href={"https://www.google.de"}>DDD</a>

                <a 
                    className={"Navbar"}
                    href={"https://www.google.de"}>AAA</a>
                <a 
                    className={"Navbar"}
                    href={"https://www.google.de"}>FFF</a>
            </div>
        ) 
    }
}

export default Navbar