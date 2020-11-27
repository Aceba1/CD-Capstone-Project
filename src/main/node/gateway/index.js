const express = require('express');
//const https = require('https'); //TODO: Research further

const app = express();
const port = 5910;

app.use(express.json());

// After all setup
app.listen(port, () => {
  console.log(`Listening on port ${port}`)
})