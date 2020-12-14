import React from 'react'

function FooterColumn(props) {
  return (
    <div className="footer-row" style={{display: "flex", flexDirection: "column", margin: "auto 40px"}}>
      <label>{props.name}</label>
      {props.children}
    </div>
  )
};

export default function Footer() {
  return (
    <div className="footer" >
      <FooterColumn name="Beans">
        <li>Garbanzos</li>
        <li>Kidney</li>
        <li>Black</li>
        <li>Dark Kidney</li>
        <li>Lentil</li>
        <li>Roman</li>
      </FooterColumn>
      <FooterColumn name="Project">
        <li>Flowchart</li>
        <li>Something else</li>
      </FooterColumn>
    </div>
  )
}
