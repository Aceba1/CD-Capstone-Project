import { useContext, useEffect, useState } from "react";
import { ErrorContext } from "../contexts/ErrorContext";
import gateway from "../utils/gateway";
import { get, set } from "../utils/storage";

const initJwt = get("jwt", null);
const initName = get("cache_userName", null);
const initEmail = get("cache_userEmail", null);

export default function useUserState() {
  const { reportTop } = useContext(ErrorContext);

  const [loggedIn, setLoggedIn] = useState(() => initJwt && initJwt.length !== 0);
  const [verified, setVerified] = useState(false);
  const [jwt, setJwt] = useState(initJwt);
  const [name, setName] = useState(initName);
  const [email, setEmail] = useState(initEmail);
  const [id, setId] = useState(null);

  useEffect(() => set("jwt", jwt), [jwt]);
  useEffect(() => set("cache_userName", name), [name]);
  useEffect(() => set("cache_userEmail", email), [email]);

  const disconnect = () => {
    setJwt(null);
    setName(null);
    setEmail(null);
    reportTop("warn", "You have been disconnected");
  }

  const checkVerify = (status, response) => {
    if (status >= 400) {
      disconnect();
    } else {
      setJwt(response.jwt);
    }
    console.log(status);
    console.log(response);
  }

  const verifyJwt = () => {
    gateway("userVerify", { jwt }, checkVerify)
  }

  return { 
    jwt, setJwt, verifyJwt,
    name, email, id
  }
}