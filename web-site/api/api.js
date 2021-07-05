const axios = require("axios");
const qs = require("qs");
import config from '@config';

const SITE =config("SITE");
const SERVER=config("SERVER");

const header={
  'Accept': 'application/json',
  "Content-Type": "application/json",
  "site":SITE,
}

export async function post(url, requestData) {
  const result = await axios.post(SERVER+url,requestData, {  headers: header });
  return result.data;
}

export async function postString(url, requestData) {
  let ret = SERVER+ url +'?'+qs.stringify(requestData);
  const result = await axios.post(ret, {  },{  headers: header  });
  return result.data;
}

