import React from 'react'
import Button from './Button'
import Spacer from './Spacer'

export default function NavBar() {
  return (
    <nav style={{display: "flex", flexDirection: "row"}}>
      <Button text="Home" onClick={() => {window.location = "/"}} className="navbar-button button-login" />
      <Spacer />
      <Button text="Tables" onClick={() => {window.location = "/tables"}} className="navbar-button button-login" />
      <Button text="Login" onClick={() => {window.location = "/user/login"}} className="navbar-button button-login" />
    </nav>
  )
}
