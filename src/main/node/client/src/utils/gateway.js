import config from './gatewayConfig.json'

export default function gateway(action, body, onSuccess, onError) {
  fetch(config.endpoint, {
    body: JSON.stringify({
      action: action,
      body: body
    }),
    method: config.method
  }).then(r => onSuccess(r.status, r.json))
  .catch(onError)
}