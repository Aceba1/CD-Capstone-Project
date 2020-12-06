const express = require('express');
const routes = require("./routes");

const router = express.Router();

function missingRoute(action) {
  return {
    error: 'Bad Gateway Route',
    status: 400,
    message: "Route '" + action + "' is unavailable",
    //TODO: REMOVE / APPEND TO ENV VARIABLE
    __dev_tools__: {
      actions: Object.keys(routes)
    }
  };
}

/**
 * @param {Request} req 
 * @param {Response} res 
 */
async function processApiSwitch(req, res) {
  const {action, body} = req.body;
  const route = routes[action];
  if (route)
    await route(body, res);
  else
    res.status(404).json(missingRoute(action));
}

router.post('/', processApiSwitch);

module.exports = router;