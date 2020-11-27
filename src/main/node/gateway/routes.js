const { default: axios } = require("axios")

const 
  userAddr = 'http://localhost:5911', //TODO: Multi-microservice distributor
  processorAddr = 'http://localhost:5913',

  userLogin = process.env['users.map.login'],
  userRegister = process.env['users.map.register'],
  
  procTransactionPage = '/test/db',
  procTransactionCount = '/test/db/count',
  procTransactionUploadCSV = '/test/db/csv';

async function request(url, data, method, response) {
  try {
    const result = await axios.request({
      url: url,
      method: method,
      data: data
    });
    response.status(result.status).json(result.data)
  } catch (e) {
    //console.log(e.response);
    if (e.response === undefined)
      response.status(503).json({ 
        status: 503, 
        message: "Service is down"
      });
    else
      response.status(e.response.status).json({ 
        status: e.response.status, 
        message: e.response.data
      });
  }
}

module.exports = {
  
  /** Standard layout
    * @param {{}} body
    * @param {Response} res 
    */

  userLogin: async (body, res) => {
    console.log("Passed userLogin")
    console.log(body);
    await request(userAddr + userLogin, 
      body, 'PUT', res);
  },

  userRegister: async (body, res) => {
    console.log("Passed userRegister")
    console.log(body);
    await request(userAddr + userRegister, 
      body, 'POST', res);
  },
}