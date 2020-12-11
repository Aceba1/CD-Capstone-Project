import React from 'react'
import PropTypes from 'prop-types'

function Input(props) {
  return (
    <input 
      className={props.className}
      value={props.value}
      onChange={e=>props.setValue(e.target.value)}
      placeholder={props.ph}
    />
  )
}

Input.propTypes = {
  value: PropTypes.string,
  setValue: PropTypes.func,
  ph: PropTypes.string,
  className: PropTypes.string
}

export default Input

