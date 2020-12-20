import React from 'react'
import PropTypes from 'prop-types'

function Input(props) {
  return (
    <input 
      type={props.type??"text"}
      className={props.className}
      value={props.value}
      onChange={e=>props.setValue(e.target.value)}
      placeholder={props.ph}
      required={props.required}
    />
  )
}

Input.propTypes = {
  value: PropTypes.string,
  setValue: PropTypes.func,
  ph: PropTypes.string,
  className: PropTypes.string,
  type: PropTypes.string,
  required: PropTypes.bool
}

export default Input

