import React, { useContext } from 'react'
import { UserContext } from '../../contexts/UserContext'
import useEnsureVerifiedState from '../../hooks/useEnsureVerifiedState'
import Button from '../Button'
import BasicPage from './BasicPage'

function UserManagementPage() {
  const {disconnect, verified} = useContext(UserContext);
  useEnsureVerifiedState(() => {}, () => {
    window.location = "/user/login?redir=/user";
  })

  return (
    <BasicPage className="page-user">
      <h1>Manage User</h1>
      { !verified ? (
        <>
        <span style={{fontStyle: "italic"}}>Loading...</span>
        </>
      ) : (
        <Button text="Logout" onClick={() => {
          disconnect();
          window.location = "/";
        }} />
      )}

    </BasicPage>
  )
}

export default UserManagementPage

