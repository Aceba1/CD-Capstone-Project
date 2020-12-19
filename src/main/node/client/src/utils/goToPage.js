
/**
 * 
 * @param {string} path 
 * @param {string | string[]} params Key & Value
 */
export default function goToPage(path, params = null) {
  suffix = "";
  if (params) {
    if (params instanceof Array) {
      suffix = "?" + encodeURI(params[0]);
      i = 0;
      while (++i < params.length) {
        suffix += "&" + encodeURI(params[i]);
      }
    }
    else if (params instanceof String)
      suffix = "?" + encodeURI(params);
    else
      console.error("goToPage.js: Cannot use params of type " + typeof(params));
  }

  window.location = path + suffix;
}