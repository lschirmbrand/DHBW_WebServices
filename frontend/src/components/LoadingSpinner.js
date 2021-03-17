import React from 'react'
import Spinner from 'react-bootstrap/esm/Spinner';

import './LoadingSpinner.css'

export default function LoadingSpinner () {
    return (
        <div className="spinner">
            <Spinner animation="border" />
        </div>
    )
}
