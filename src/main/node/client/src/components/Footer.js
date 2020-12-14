import React from 'react'
import Hyperlink from './Hyperlink'

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
        <li><Hyperlink 
          url="https://github.com/Aceba1/CD-Capstone-Project"
          text="Github"/></li>
        <li><Hyperlink 
          url="https://github.com/Aceba1/CD-Capstone-Project/blob/main/README.md#diagram"
          text="Flowchart"/></li>
        <li>Something else</li>
      </FooterColumn>
    </div>
  )
}
