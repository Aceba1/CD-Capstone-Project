import React, { createContext, useState } from 'react'

export const UserContext = createContext()

export default function UserContextProvider(props) {
  const [state, setstate] = useState(initialState)
  return (
    
    <UserContext.Provider value={[state, setstate]}>
      {props.children}
    </UserContext.Provider>
  )
}
