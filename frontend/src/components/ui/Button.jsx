import React from 'react'
import './Button.scss'

const Button = ({text,
  className,
  icons, 
  onClick, 
  backico=''
}) => {
const backIconSrc = 
  backico=='wh'?"/images/arrow-back-white.svg" : 
  backico == "bh"?"/images/arrow-back.svg" : null

  return (
    <button className={`btn ${className}`} onClick={onClick}>
      {
        backIconSrc && <img src={backIconSrc}/>
      }
      {text}
      {icons && <img src='/images/arrow.svg'/>}
    </button>
  )
}

export default Button