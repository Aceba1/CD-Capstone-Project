import { createContext, useState } from "react";

export const ErrorContext = createContext();

const _top = [];

export default function ErrorContextProvider(props) {

  const [top, setTop] = useState([])

  /** Add message to end of 'top' collection
   * @param {string} type 
   * @param {string} message */
  const reportTop = (type, message) => {
    console.log(type, message);
    _top.push({
      type: type[0],
      message: message
    });

    setTop([..._top]);
  };

  /** Remove message from 'top' collection
   * @param {number} index  */
  const removeTop = (index) => {
    _top.splice(index, 1);

    setTop([..._top]);
  }

  const clearTop = () => {
    _top.splice(0);

    setTop(_top);
  }

  return (
    <ErrorContext.Provider value={{reportTop, removeTop, top, clearTop}}>
      {props.children}
    </ErrorContext.Provider>
  )
}