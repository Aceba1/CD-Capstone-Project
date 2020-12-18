import React from 'react'
import useLoginState from '../../hooks/useLoginState';
import Button from '../Button'
import Input from '../Input'
import UserBasicPage from './UserBasicPage'

function UserLoginPage(props) {

  const { cred, setCred, pass, setPass, attemptLogin } = useLoginState();

  return (
    <UserBasicPage>
      <h1>Login User</h1>
      <Input ph="Credential" value={cred} setValue={setCred} />
      <Input ph="Password" value={pass} setValue={setPass} type="password" />
      <Button text="Login" onClick={attemptLogin} />
    </UserBasicPage>
  )
}

export default UserLoginPage

