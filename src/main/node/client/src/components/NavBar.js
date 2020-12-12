import React from 'react'
import Button from './Button'

export default function NavBar() {
  return (
    <nav style={{display: "flex", flexDirection: "row"}}>
      <Button text="Tables" onClick={() => {window.location = "/table"}} className="navbar-button button-login" />
      <Button text="Login" onClick={() => {window.location = "/table"}} className="navbar-button button-login" />
    </nav>
  )
}
