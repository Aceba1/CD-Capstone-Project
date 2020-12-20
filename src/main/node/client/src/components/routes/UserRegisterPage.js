import React from 'react'
import FormBasicPage from './FormBasicPage'

function UserRegisterPage(props) {
  return (
    <FormBasicPage onSubmit={attemptRegister} >
    </FormBasicPage>
  )
}

export default UserRegisterPage

