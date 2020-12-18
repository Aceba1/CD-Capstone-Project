import React, { useContext } from 'react'
import { ErrorContext } from '../contexts/ErrorContext';
import Card from './Card'
import PropTypes from 'prop-types'

function ErrorTop(props) {
  const {type, message} = props.error;

  return (
    <Card className={"error-top error-type-" + type} onClick={props.onClick}>
      {message}
    </Card>
  )
}

function ErrorTopDisplay(props) {
  const {top, removeTop} = useContext(ErrorContext);
  
  //TODO: Have cards fixed over emptiness of page, change pointer, show X
  return (
    <div className={"error-top-list " + props.className}>
      {
        top.map((item, index) => {
          return (
            <ErrorTop error={item} key={index} onClick={() => removeTop(index)} />
          )
        })
      }
    </div>
  )
}

ErrorTopDisplay.propTypes = {
  className: PropTypes.string
}

export default ErrorTopDisplay

