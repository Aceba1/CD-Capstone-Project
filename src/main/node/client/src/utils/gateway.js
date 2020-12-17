import Axios from 'axios'
import config from '../json/gatewayConfig.json'
/**
 * 
 * @param {string} action 
 * @param {{} | string} body 
 * @param {(status: number, body: {}) => void} onSuccess 
 * @param {(reason: any) => void} onError 
 */
export default function gateway(action, body, onSuccess, onError) {
  Axios.request({
    url: config.endpoint,
    method: config.method,
    data: {
      action,
      body
    }
  }).then(r => onSuccess(r.status, r.data))
  .catch(onError)
}