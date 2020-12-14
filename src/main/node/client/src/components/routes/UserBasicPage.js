import React from 'react'
import NavBar from '../NavBar'
//import PropTypes from 'prop-types'

function UserBasicPage(props) {
  return (
    <>
      <NavBar />
      <div className="page page-user-card card-bg">
        <div className="centered card"> 
          {props.children}
        </div>
      </div>
    </>
  )
}

// UserBasicPage.propTypes = {
//   children: PropTypes.node
// }

export default UserBasicPage

