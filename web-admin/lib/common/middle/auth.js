import config from '../config';
import { paramsSign } from '../util/Sign';
const axios = require("axios");
const qs = require("qs");

//验证是否登录
export async function login_required(ctx, next){
   const token=ctx.cookies.get("sessionId");
   let header=null;
   if(token){
        ctx.token=token;
        header={
          'Accept': 'application/json',
          "Content-Type": "application/json",
          "token":token
        }
    }else{
        header={
          'Accept': 'application/json',
          "Content-Type": "application/json"
        }
    }
    
    ctx.postJson=async function postJson(url,requestData){
      const server=config('server');
      const sign=paramsSign('POST',server+url,requestData);
      requestData['sign']=sign;
      const result = await axios.post(server + url,
      requestData,
      {
        headers: header
      });
      return result.data;
    };

    ctx.postString=async function postString(url,requestData){
        const server=config('server');
        const sign=paramsSign('POST',server+url,{});
        let ret = server+ url +'?'+qs.stringify(requestData);
        const result = await axios.post(ret, {sign},{
          headers: header
        });
        return result.data;
    };

    ctx.getHTML=async function postString(url,requestData){
      const server=config('server');
      const sign=paramsSign('POST',server+url,{});
      let ret = server+ url +'?'+qs.stringify(requestData);
      let result= await axios.post(ret, {sign});
      return result;
  }

   await next();
}


export async function get(url,requestData){
  const server=config('server');
  let ret = server+ url +'?'+qs.stringify(requestData);
  let result= await axios.get(ret, {});
  return result;
}