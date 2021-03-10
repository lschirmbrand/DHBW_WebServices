import React from 'react';

import './Header.css';

class Header extends React.Component {
    render() {
        return (
            <div className={'header'}>
                <a href="/">Soundtrack Guesser</a>
            </div>
        );
    }
}

export default Header;
