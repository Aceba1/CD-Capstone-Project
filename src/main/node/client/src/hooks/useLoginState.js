import { useContext, useState } from "react";
import { ErrorContext } from "../contexts/ErrorContext";
import { UserContext } from "../contexts/UserContext";
import gateway from "../utils/gateway";

export default function useLoginState() {
  const { connect } = useContext(UserContext);
  const { reportTop } = useContext(ErrorContext);

  const [cred, setCred] = useState("");
  const [pass, setPass] = useState("");

  const checkSuccess = (status, body) => {
    console.log("Received")
    console.log(status)
    console.log(body)
    if (status < 400)
      connect(body);
    else
      reportTop("error", "Failed to login: " + body.message);
  };

  const onFail = (status, body) => {
    console.log("Received error")
    console.log(status)
    console.log(body)
    reportTop("error", body.message);
  };

  const attemptLogin = () => {
    gateway("userLogin", {
      credential: cred,
      password: pass
    }, checkSuccess, onFail )
  };

  return { 
    cred, setCred, 
    pass, setPass,
    attemptLogin 
  };
}