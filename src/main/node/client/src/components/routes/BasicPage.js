import React from 'react'
import Footer from '../Footer'
import PropTypes from 'prop-types'
import NavBar from '../NavBar'

function BasicPage(props) {
  return (
    <>
      <NavBar />
      <div className={"page " + props.className}>
        {props.children}
      </div>
      <Footer />
    </>
  )
}

BasicPage.propTypes = {
  children: PropTypes.node,
  className: PropTypes.string
}

export default BasicPage

