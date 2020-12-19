
/**
 * 
 * @param {string} path 
 * @param {string | string[]} params Key & Value
 */
export function goTo(path, params = null) {
  suffix = "";
  if (params) {
    if (params instanceof Array) {
      suffix = "?" + encodeURI(params[0]);
      i = 0;
      while (++i < params.length)
        suffix += "&" + encodeURI(params[i]);
    }
    else if (params instanceof String)
      suffix = "?" + encodeURI(params);
    else
      console.error("goToPage.js: Cannot use params of type " + typeof(params));
  }

  window.location = path + suffix;
}

/**
 * 
 * @param {string} path 
 * @param {URLSearchParams} params 
 */
export function goToWithParam(path, params) {
  window.location = path + "?" +  params.toString();
}

export function goToParam(defaultPath = "/") {

}