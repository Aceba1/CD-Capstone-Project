//import { useState } from "react";
import AppRouter from "./components/AppRouter";
import UserContextProvider from "./contexts/UserContext";
import ErrorContextProvider from "./contexts/ErrorContext";

//import Dropzone from "react-dropzone";

export default function App() {

  return (
    <div className="App">
      <ErrorContextProvider>
        <UserContextProvider>
          <AppRouter/>
        </UserContextProvider>
      </ErrorContextProvider>
    </div>
  );
}