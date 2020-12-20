import React from 'react'
import PropTypes from 'prop-types'

function ButtonSubmit(props) {
  return (
    <input
      type="submit"
      className={props.className}
      value={props.text}
    />
  )
}

ButtonSubmit.propTypes = {
  text: PropTypes.string,
  className: PropTypes.string
}

export default ButtonSubmit

