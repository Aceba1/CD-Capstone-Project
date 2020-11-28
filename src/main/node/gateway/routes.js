const { default: axios } = require("axios")

const 
  userAddr = 'http://localhost:5911', //TODO: Multi-microservice distributor
  procAddr = 'http://localhost:5913',

  userLogin = process.env['users.map.login'],
  userRegister = process.env['users.map.register'],
  
  procTransactionPage = process.env['proc.map.tr.page'],
  procTransactionCount = process.env['proc.map.tr.count'],
  procTransactionUploadCSV = process.env['proc.map.tr.upload'];

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
        message: "Service is down",
        status: 503
      });
    else
      response.status(e.response.status).json({ 
        ...e.response.data,
        status: e.response.status, 
      });
  }
}

module.exports = {
  
  /** Standard layout
    * @param {{}} body
    * @param {Response} res 
    */

  userLogin: async (body, res) => {
    await request(userAddr + userLogin, 
      body, 'PUT', res);
  },

  userRegister: async (body, res) => {
    await request(userAddr + userRegister, 
      body, 'POST', res);
  },

  procTransactionPage: async (body, res) => {
    await request(procAddr + procTransactionPage,
      body, 'GET', res);
  },

  procTransactionCount: async (body, res) => {
    await request(procAddr + procTransactionCount,
      body, 'GET', res);
  },

  procTransactionUpload: async (body, res) => {
    await request(procAddr + procTransactionUpload,
      body, 'POST', res);
  }
}