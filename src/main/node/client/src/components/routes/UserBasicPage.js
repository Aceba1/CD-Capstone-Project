import React from 'react'
import BasicPage from './BasicPage'
//import PropTypes from 'prop-types'

function UserBasicPage(props) {
  return (
    <BasicPage>
      <div className="bg"
        style={{backgroundColor:"eee"}}
      >
        <div className="centered card"> 
          {props.children}
        </div>
      </div>
    </BasicPage>
  )
}

// UserBasicPage.propTypes = {
//   children: PropTypes.node
// }

export default UserBasicPage

