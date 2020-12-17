export default function isUnset(value) {
  return value === undefined || value === null || (value instanceof String && value.trim === 0);
}
