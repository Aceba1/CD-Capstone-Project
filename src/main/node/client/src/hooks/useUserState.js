import { useContext, useEffect, useState } from "react";
import { ErrorContext } from "../contexts/ErrorContext";
import gateway from "../utils/gateway";
import { get, set } from "../utils/storage";

const initJwt = get("jwt", "");
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

  const connect = (body) => {
    if (body) {
      setName(body.name);
      setEmail(body.email);
      setJwt(body.jwt);
      setId(body.userId);
    } else {
      console.log("Body is missing!")
    }
    if (loggedIn) {
      reportTop("info", "Verification success");
    } else {
      reportTop("info", "Login success");
    }
    setLoggedIn(true);
    setVerified(true);
  }

  const disconnect = () => {
    if (loggedIn) {
      reportTop("warn", "You have been disconnected");
    }
    setJwt(null);
    setName(null);
    setEmail(null);
    setId(null);
    setLoggedIn(false);
    setVerified(false);
  }

  const checkVerify = (status, response) => {
    console.log(status);
    console.log(response);
    if (status < 400) {
      connect(response)
    } else {
      disconnect();
    }
  }

  const verifyJwt = () => {
    gateway("userVerify", { jwt }, checkVerify, (_status, data) => {
      console.log(data);
      reportTop("err", data.message);
      disconnect();
    })
  }

  return { 
    jwt, setJwt, verifyJwt,
    name, email, id,
    connect, disconnect,
    loggedIn, verified
  }
}