import React, { createContext } from 'react'
import useUserState from '../hooks/useUserState'

export const UserContext = createContext()



export default function UserContextProvider(props) {
  const userState = useUserState();

  return (

    <UserContext.Provider value={userState}>
      {props.children}
    </UserContext.Provider>
  )
}
