import React from "react"

import Title from "./Title"

class Header extends React.Component{
    render(){
        return(
            <div className={"Header"}>
                <Title />                
            </div>
        )
    }
}

export default Header