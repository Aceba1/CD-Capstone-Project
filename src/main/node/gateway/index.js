require('dotenv').config({ path: require('path').resolve(
  process.cwd(), '../../resources/application.properties') });

const express = require('express');
const cors = require('cors');
//const https = require('https'); //TODO: Research further

const apiSwitch = require('./apiSwitch');

const app = express();
const port = 5910;

app.use(express.json());

app.use(cors());
app.use(apiSwitch);

// After all setup
app.listen(port, () => {
  console.log(`Listening on port ${port}`)
})