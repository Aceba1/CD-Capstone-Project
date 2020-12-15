const { default: axios } = require("axios")

const 
  userAddr = 'http://localhost:5911', //TODO: Multi-microservice distributor
  procAddr = 'http://localhost:5913',

  userLogin = process.env['users.map.login'],
  userRegister = process.env['users.map.register'],
  userVerify = process.env['users.map.verify'],
  
  procTransactionPage = process.env['proc.map.tr.page'],
  procTransactionCount = process.env['proc.map.tr.count'],

  procTransactionUploadCSV = process.env['proc.map.tr.upload.csv'],
  procTransactionSingle = process.env['proc.map.tr.single'];

async function request(url, data, method, response, params = undefined, headers = undefined) {
  try {
    const result = await axios.request({
      url: url,
      method: method,
      data: data,
      params: params,
      headers: headers
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

  userVerify: async (body, res) => {
    await request(userAddr + userVerify, 
      body, 'GET', res);
  },

  procTransactionPage: async (body, res) => {
    await request(procAddr + procTransactionPage,
      body, 'GET', res, body);
  },

  procTransactionCount: async (body, res) => {
    await request(procAddr + procTransactionCount,
      body, 'GET', res);
  },

  procTransactionUploadCSV: async (body, res) => {
    await request(procAddr + procTransactionUploadCSV,
      body, 'POST', res);
  },

  procTransactionGetSingle: async (body, res) => {
    await request(procAddr + procTransactionSingle,
      body, 'GET', res);
  },

  procTransactionPutSingle: async (body, res) => {
    await request(procAddr + procTransactionSingle,
      body, 'PUT', res);
  },

  procTransactionDeleteSingle: async (body, res) => {
    await request(procAddr + procTransactionSingle,
      body, 'DELETE', res);
  }
}