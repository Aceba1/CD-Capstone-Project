import React, { useContext, useState } from 'react'
import { ErrorContext } from '../../contexts/ErrorContext'
import { UserContext } from '../../contexts/UserContext'
import gateway from '../../utils/gateway'
import Button from '../Button'
import Input from '../Input'
import UserBasicPage from './UserBasicPage'

function UserLoginPage(props) {
  const { connect } = useContext(UserContext);
  const { reportTop } = useContext(ErrorContext);

  const [cred, setCred] = useState("");
  const [pass, setPass] = useState("");

  const attemptLogin = () => {
    gateway("userLogin", {
      credential: cred,
      password: pass
    }, (status, body) => {
      console.log("Received")
      console.log(status)
      console.log(body)
      if (status < 400)
        connect(body);
      else
        reportTop("error", "Failed to login: " + body.message);
    }, (status, body) => {
      console.log("Received error")
      console.log(status)
      console.log(body)
      reportTop("error", body.message);
    } )
  }

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

