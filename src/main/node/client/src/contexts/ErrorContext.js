import { createContext, useState } from "react";

export const ErrorContext = createContext();

export default function ErrorContextProvider(props) {

  const [top, setTop] = useState([])

  /** Add message to end of 'top' collection
   * @param {string} type 
   * @param {string} message */
  const reportTop = (type, message) => {
    const temp = [...top];
    
    temp.push({
      type: type[0],
      message: message
    })

    setTop(temp);
  }

  /** Remove message from 'top' collection
   * @param {number} index  */
  const removeTop = (index) => {
    const temp = [...top];

    temp.splice(index, 1);

    setTop(temp);
  }

  return (
    <ErrorContext.Provider value={{reportTop, removeTop, top}}>
      {props.children}
    </ErrorContext.Provider>
  )
}