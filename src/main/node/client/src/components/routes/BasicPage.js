import React from 'react'
import Footer from '../Footer'
import PropTypes from 'prop-types'
import NavBar from '../NavBar'
import ErrorTopDisplay from '../ErrorTopDisplay'

function BasicPage(props) {
  return (
    <>
      <NavBar />
      <ErrorTopDisplay />
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

