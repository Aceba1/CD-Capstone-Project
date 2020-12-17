import React, { useContext } from 'react'
import { ErrorContext } from '../contexts/ErrorContext';
import Card from './Card'

function ErrorTop(props) {
  const {type, message} = props.error;

  return (
    <Card className={"error-top error-type-" + type}>
      {message}
    </Card>
  )
}

export default function ErrorTopDisplay() {
  const {top, removeTop} = useContext(ErrorContext);
  
  return (
    <div className="error-top-list">
      {
        top.map((item, index) => {
          return (
            <ErrorTop error={item} key={index} />
          )
        })
      }
    </div>
  )
}
