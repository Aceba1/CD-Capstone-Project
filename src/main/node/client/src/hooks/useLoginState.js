import { useContext, useState } from "react";
import { ErrorContext } from "../contexts/ErrorContext";
import { UserContext } from "../contexts/UserContext";
import gateway from "../utils/gateway";

export default function useLoginState() {
  const { connect } = useContext(UserContext);
  const { reportTop, clearTop } = useContext(ErrorContext);

  const [cred, setCred] = useState("");
  const [pass, setPass] = useState("");

  const checkSuccess = (status, body) => {
    if (status < 400) {
      connect(body);
      window.location = new URLSearchParams(window.location.search).get("redir") ?? "/";
    }
    else
      reportTop("error", "Failed to login: " + body.message);
  };

  const onFail = (_status, body) => {
    reportTop("error", body.message);
  };

  const attemptLogin = () => {
    clearTop();
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