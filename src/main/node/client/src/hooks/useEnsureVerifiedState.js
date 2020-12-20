import { useContext, useEffect, useState } from 'react';
import { UserContext } from "../contexts/UserContext";

var firstCache = true;

//TODO: Replace with better method!
export default function useEnsureVerifiedState(onPass = () => {}, onFail = () => {}, onNotLoggedIn = () => {}) {
  const { loggedIn, verified, verifyJwt } = useContext(UserContext);

  const [checkingLogin, setChecking] = useState(() => {
    const result = loggedIn && !verified;
    if (firstCache) {
      if (result)
        verifyJwt();
      else if (onNotLoggedIn) 
        onNotLoggedIn();
    }
    firstCache = false;
    return result;
  });

  useEffect(() => {
    if (checkingLogin && verified) {
      setChecking(false);
      if (onPass) onPass();
    }
    else if (checkingLogin && !loggedIn) {
      setChecking(false);
      if (onFail) onFail();
    }
  }, [verified, loggedIn]); // checkingLogin, onPass, onFail

  return checkingLogin;
}