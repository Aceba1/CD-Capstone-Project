import { useContext, useEffect, useState } from 'react';
import { UserContext } from "../contexts/UserContext";

var firstCache = true;

//TODO: Replace with better method!
export default function useEnsureVerifiedState(onPass = () => {}, onFail = () => {}) {
  const { loggedIn, verified, verifyJwt } = useContext(UserContext);

  const [checkingLogin, setChecking] = useState(() => {
    const result = loggedIn && !verified;
    if (result && firstCache) {
      verifyJwt();
    }
    firstCache = false;
    return result;
  });

  useEffect(() => {
    if (verified)
      onPass();
    else if (!loggedIn) {
      setChecking(false);
      onFail();
    }
  }, [verified, loggedIn]);

  return checkingLogin;
}