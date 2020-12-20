import React, { useContext } from 'react'
import { UserContext } from '../../contexts/UserContext'
import useEnsureVerifiedState from '../../hooks/useEnsureVerifiedState'
import Button from '../Button'
import BasicPage from './BasicPage'

function UserManagementPage() {
  const {disconnect, verified, name, email} = useContext(UserContext);
  
  const goToLogin = () => window.location = "/user/login?redir=%2Fuser";

  useEnsureVerifiedState(null, goToLogin, goToLogin)

  return (
    <BasicPage className="page-user">
      <h1>Manage User</h1>
      { !verified ? (
        <>
        <span style={{fontStyle: "italic"}}>Loading...</span>
        </>
      ) : (
        <>
        <p>Username: <code>{name}</code></p>
        <p>Email: <code>{email}</code></p>
        <Button text="Logout" onClick={() => {
          disconnect();
          window.location = "/";
        }} />
        </>
      )}

    </BasicPage>
  )
}

export default UserManagementPage

