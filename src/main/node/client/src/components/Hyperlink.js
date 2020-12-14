import React from 'react'
import PropTypes from 'prop-types'

function Hyperlink(props) {
  return (
    <a 
      href={props.url}
      target="_blank"
      rel="noreferrer"
    >
      {props.text}
    </a>
  )
}

Hyperlink.propTypes = {
  url: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired
}

export default Hyperlink

