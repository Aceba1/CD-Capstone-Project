import React, { useState } from 'react'
import useEnsureVerifiedState from '../../hooks/useEnsureVerifiedState';
import useRegisterState from '../../hooks/useRegisterState';
import Button from '../Button';
import ButtonSubmit from '../ButtonSubmit';
import Input from '../Input';
import FormBasicPage from './FormBasicPage'

function UserRegisterPage(props) {
  const {
    name, setName, email, setEmail,
    pass, setPass, pass2, setPass2,
    attemptRegister
  } = useRegisterState();

  const checkingLogin = useEnsureVerifiedState(() => {
    window.location = new URLSearchParams(window.location.search).get("redir") ?? "/";
  });

  return (
    <FormBasicPage onSubmit={attemptRegister} >
      <h1>Register User</h1>
      { checkingLogin ? (
        <>
        <span style={{fontStyle: "italic"}} >Logging in...</span>
        </>
      ) : (
        <>
        <Input ph="Username" value={name} setValue={setName} required /><br />
        <Input ph="Email" value={email} setValue={setEmail} type="email" required /><br />
        <Input ph="Password" value={pass} setValue={setPass} type="password" required /><br />
        <Input ph="Retype Password" value={pass2} setValue={setPass2} type="password" required /><br />
        
        <ButtonSubmit text="Register" /><br />  
        <br />
        <div>
          Have an account? 
          <Button text="Login" onClick={() => window.location = "/user/login" + window.location.search} />
        </div>
        </>
      )}
    </FormBasicPage>
  )
}

export default UserRegisterPage

