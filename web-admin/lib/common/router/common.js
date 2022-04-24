var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

let OSS = require('ali-oss');
import config from '@server/common/config';
const koaBody = require('koa-body');
var path=require('path');  
var uuid = require('uuid/v4');
var svgCaptcha = require('svg-captcha');

let client = new OSS({
  region: config('region'),
  accessKeyId: config('accessKeyId'),
  accessKeySecret: config('accessKeySecret'),
  bucket: config('bucketName')
})


/**
 * 文件上传
 */
router.post('/upload', koaBody(),login_required,async(ctx,next)=>{
  const {file}=ctx.request.files;
  const extname=path.extname(file.name);	 //获取文件的后缀名
  let result = await client.put(uuid().replace(/-/g, '')+extname,file.path); 
  ctx.body={code:'SUCCESS',msg:"上传成功",path:result.url};
});

/**
 * 验证码
 */
router.post('/captcha',login_required, async(ctx)=>{
    var captcha = svgCaptcha.create({
     width: 120,  
     height: 50, 
   });
   const pic_verify=captcha.text;
   const {code}=await ctx.postJson('/common/captcha/save',{pic_verify});
	console.log(code)
   if(code=='SUCCESS'){
      ctx.body={
        svg: captcha.data,
      };
   }else{
     ctx.body={svg: null,key:null};
   }
  
});

module.exports = router;


