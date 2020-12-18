import React from 'react'
import Card from '../Card'
import ErrorTopDisplay from '../ErrorTopDisplay'
import NavBar from '../NavBar'
//import PropTypes from 'prop-types'

function UserBasicPage(props) {
  return (
    <>
      <NavBar />
      <ErrorTopDisplay />
      <div className="page page-user-card card-bg centered">
        <Card className="float"> 
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

