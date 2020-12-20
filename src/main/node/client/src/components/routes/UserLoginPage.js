import React from 'react'
import useLoginState from '../../hooks/useLoginState';
import Button from '../Button'
import Input from '../Input'
import FormBasicPage from './FormBasicPage'
import useEnsureVerifiedState from "../../hooks/useEnsureVerifiedState";
import ButtonSubmit from '../ButtonSubmit';

function UserLoginPage() {

  const { cred, setCred, pass, setPass, attemptLogin } = useLoginState();
  const checkingLogin = useEnsureVerifiedState(() => {
    window.location = new URLSearchParams(window.location.search).get("redir") ?? "/";
  });

  return (
    <FormBasicPage onSubmit={attemptLogin} >
      <h1>Login User</h1>
      { checkingLogin ? (
        <>
        <span style={{fontStyle: "italic"}} >Logging in...</span>
        </>
      ) : (
        <>
        <Input ph="Username or Email" value={cred} setValue={setCred} required /><br />
        <Input ph="Password" value={pass} setValue={setPass} type="password" required /><br />
        <ButtonSubmit text="Login" /><br />
        <br />
        <div>
          Need an account?
          <Button text="Register" onClick={() => window.location = "/user/register" + window.location.search} />
        </div>
        </>
      )}
    </FormBasicPage>
  )
}

export default UserLoginPage

