import React, { useContext } from 'react'
import { UserContext } from '../contexts/UserContext'
import Button from './Button'
import Spacer from './Spacer'

export default function NavBar() {
  const { loggedIn, name } = useContext(UserContext);
  return (
    <div className="header">
      <label className="header-title" onClick={() => {window.location = "/"}}>
        Prototype Site
      </label>
      <nav style={{display: "flex", flexDirection: "row"}}>
        <Button text="Home" onClick={() => {window.location = "/"}} className="navbar-button button-login" />
        <Spacer />
        <Button text="Tables" onClick={() => {window.location = "/tables"}} className="navbar-button button-login" />
        {
          loggedIn ? (
            <Button text={"User " + name} onClick={() => {window.location = "/user"}} className="navbar-button button-user" />
          ) : (
            <Button text="Login" onClick={() => {window.location = "/user/login"}} className="navbar-button button-login" />
          )
        }
      </nav>
    </div>
  )
}
