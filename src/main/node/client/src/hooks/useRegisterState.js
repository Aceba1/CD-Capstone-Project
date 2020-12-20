import { useContext, useState } from "react";
import { ErrorContext } from "../contexts/ErrorContext";
import { UserContext } from "../contexts/UserContext";
import gateway from "../utils/gateway";

const MIN_USERNAME_LENGTH = 3;
const MAX_USERNAME_LENGTH = 20;
const MIN_PASSWORD_LENGTH = 6;
const MAX_PASSWORD_LENGTH = 100;

//TODO: Find something that already exists
const EMAIL_REGEX = /^.+@.+[:.].+$/;
const USER_REGEX = /^[A-Za-z0-9_ \\-]+$/;

export default function useRegisterState() {
  const { connect } = useContext(UserContext);
  const { clearTop, reportTop } = useContext(ErrorContext);

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [pass2, setPass2] = useState("");

  const checkSuccess = (status, body) => {
    if (status < 400) {
      connect(body);
      window.location = new URLSearchParams(window.location.search).get("redir") ?? "/";
    }
    else
      reportTop("error", "Failed to register: " + body.message);
  };

  const onFail = (_status, body) => {
    reportTop("error", body.message);
  };

  const attemptRegister = () => {
    var errors = 0;
    clearTop();

    if (name.length < MIN_USERNAME_LENGTH) {
      errors++;
      reportTop("warn", "Name is too short (<" + MIN_USERNAME_LENGTH + ")");
    } else if (name.length > 20) {
      errors++;
      reportTop("warn", "Name is too long (>" + MAX_USERNAME_LENGTH + ")");
    }
    else if (!USER_REGEX.test(name)) {
      errors++;
      reportTop("warn", "Name has invalid characters");
    }
    if (!EMAIL_REGEX.test(email)) {
      errors++;
      reportTop("warn", "Email is invalid");
    }
    if (pass.length < MIN_PASSWORD_LENGTH) {
      errors++;
      reportTop("warn", "Password is too short (<" + MIN_PASSWORD_LENGTH + ")");
    } else if (pass.length > MAX_PASSWORD_LENGTH) {
      errors++;
      reportTop("warn", "Password is too long (>" + MAX_PASSWORD_LENGTH + ")");
    }
    if (pass !== pass2) {
      errors++;
      reportTop("warn", "Passwords do not match");
    }

    if (errors === 0)
      gateway("userRegister", {
        name: name,
        email: email,
        password: pass
      }, checkSuccess, onFail )
  };

  return { 
    name, setName, 
    email, setEmail,
    pass, setPass,
    pass2, setPass2,
    attemptRegister
  };
}