import React from 'react'
import Card from '../Card'
import ErrorTopDisplay from '../ErrorTopDisplay'
import NavBar from '../NavBar'
import PropTypes from 'prop-types'

function FormBasicPage(props) {
  return (
    <>
      <NavBar />
      <ErrorTopDisplay />
      <div className="page page-user-card card-bg centered">
        <Card className="float">
          <form onSubmit={e => {e.preventDefault(); props.onSubmit()}}> 
            {props.children}
          </form>
        </Card>
      </div>
    </>
  )
}

FormBasicPage.propTypes = {
  onSubmit: PropTypes.func.isRequired,
  children: PropTypes.node
}

export default FormBasicPage

