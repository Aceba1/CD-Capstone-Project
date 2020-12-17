import { createContext, useState } from "react";

export const ErrorContext = createContext();

function ErrorContextProvider(props) {

  const [top, setTop] = useState([])

  /** Add message to end of 'top' collection
   * @param {string} type 
   * @param {string} message */
  const reportTop = (type, message) => {
    const temp = top;
    top = null;
    
    temp.push({
      type: type[0],
      message: message
    })
    setTop(temp);
  }

  /** Remove message from 'top' collection
   * @param {number} index  */
  const removeTop = (index) => {
    const temp = top;
    top = null;
    temp.splice(index, 1);
  }

  return (
    <ErrorContext.Provider value={{reportTop, removeTop}}>
      {props.children}
    </ErrorContext.Provider>
  )
}

export default ErrorContext

