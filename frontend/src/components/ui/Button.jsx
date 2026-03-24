import React from 'react'
import './Button.scss'

const Button = ({text,className,icons, onClick}) => {
  return (
    <button className={`btn ${className}`} onClick={onClick}>
      {text}
      {icons && <img src='/images/arrow.svg'/>}
    </button>
  )
}

export default Button