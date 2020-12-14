import React from 'react'
//import PropTypes from 'prop-types'
import NavBar from '../NavBar'

function BasicPage(props) {
  return (
    <div>
      <NavBar />
      {props.children}
    </div>
  )
}

// BasicPage.propTypes = {
//   children: PropTypes.node
// }

export default BasicPage

