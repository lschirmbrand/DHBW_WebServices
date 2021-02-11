import React from 'react';
import Countdown from 'react-countdown';

class Timer extends React.Component {
    render(){
        return(
          <div>
              <Countdown date={Date.now() + this.getThemeLength()}>
                <span>Time is over!</span>
              </Countdown>
          </div>
        )
    }

    getThemeLength(){
        return(
            //Going to return the Length of the Song
            5000
        )
    }  
}
export default Timer