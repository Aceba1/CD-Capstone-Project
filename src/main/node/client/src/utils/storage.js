import isUnset from "./isUnset";

export function get(key, initial = undefined) {
  const local = localStorage.getItem(key);
  return isUnset(local) ? initial : local;
}

export function getJson(key, initial = undefined) {
  const local = localStorage.getItem(key);
  return isUnset(local) ? initial : JSON.parse(local);
}

export function set(key, value) {
  if (isUnset(value))
    localStorage.removeItem(key);
  else
    localStorage.setItem(key, value);
}

export function setJson(key, value) {
  if (isUnset(value))
    localStorage.removeItem(key);
  else
    localStorage.setItem(key, JSON.stringify(value));
}