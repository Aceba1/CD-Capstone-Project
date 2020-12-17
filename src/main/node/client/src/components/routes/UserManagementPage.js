import React, { useContext } from 'react'
import { UserContext } from '../../contexts/UserContext'
import Button from '../Button'
import Input from '../Input'
import BasicPage from './BasicPage'

function UserManagementPage() {
  const {jwt, setJwt, verifyJwt} = useContext(UserContext);

  return (
    <BasicPage className="page-user">
      <h1>Manage User</h1>

      {/* Temporary! */}
      <Input value={jwt} setValue={setJwt} ph="JWT" />
      <Button text="Verify JWT" onClick={verifyJwt} />

    </BasicPage>
  )
}

export default UserManagementPage

