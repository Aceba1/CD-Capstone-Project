import React from 'react'
import Card from '../Card'
import NavBar from '../NavBar'
//import PropTypes from 'prop-types'

function UserBasicPage(props) {
  return (
    <>
      <NavBar />
      <div className="page page-user-card card-bg centered">
        <Card> 
          {props.children}
        </Card>
      </div>
    </>
  )
}

// UserBasicPage.propTypes = {
//   children: PropTypes.node
// }

export default UserBasicPage

