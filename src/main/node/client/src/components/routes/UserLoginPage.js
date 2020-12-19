import React from 'react'
import useLoginState from '../../hooks/useLoginState';
import Button from '../Button'
import Input from '../Input'
import UserBasicPage from './UserBasicPage'

function UserLoginPage(props) {

  const { cred, setCred, pass, setPass, attemptLogin, checkingLogin } = useLoginState();

  return (
    <UserBasicPage>
      <h1>Login User</h1>
      { checkingLogin ? (
        <>
        <span style={{fontStyle: "italic"}} >Logging in...</span>
        </>
      ) : (
        <>
        <Input ph="Credential" value={cred} setValue={setCred} /><br />
        <Input ph="Password" value={pass} setValue={setPass} type="password" /><br />
        <Button text="Login" onClick={attemptLogin} /><br />
        <br />
        <div>
          Need an account?
          <Button text="Register" onClick={() => window.location = "/user/register" + window.location.search} />
        </div>
        </>
      )}
    </UserBasicPage>
  )
}

export default UserLoginPage

