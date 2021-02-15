import React from 'react';
import Countdown from 'react-countdown';

class Timer extends React.Component {
    render(){
        const renderer = ({ hours, minutes, seconds, completed }) => {
            if (completed) {
              // Render a completed state
              console.log("Timer over!")
              return <span>0s </span>;
            } else {
              // Render a countdown
              return <span>{seconds}s</span>;
            }
          };

        return(
          <div className={"Timer"}>
              <Countdown renderer={renderer} daysInHours={true} date={Date.now() + this.getThemeLength()}></Countdown>
            /30s
          </div>
        )
    }

    getThemeLength(){
        return(     
            30000
        )
    }  
}
export default Timer