var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

/**
 * 管理员后台登录
 */
router.post('/login',login_required,async(ctx,next)=>{
   const {user_name,password,key,verify}=ctx.request.body;
   const {data,code,msg}=await ctx.postJson('/login',{user_name,password,key,verify});
    if(code==='SUCCESS'){
      const { token }=data;
      ctx.cookies.set("sessionId",token);
    }
    ctx.body={code,msg,data}
});

router.post('/check-login',login_required,async(ctx,next)=>{
    const token=ctx.cookies.get("sessionId");
    if(token&&token!=null){
      const result=await ctx.postJson('/check-login',{token}); 
       ctx.body=result
    }else{
        ctx.body={code:"FAIL",msg:"token不存在"};
    }
});


/**
 * 登出
 */
router.post('/logout',login_required,async(ctx,next)=>{
  const token=ctx.cookies.get("sessionId");
  if(token){
     ctx.cookies.set("sessionId",null);
     ctx.body=await ctx.postJson('/logout',{token});
  }else{
    ctx.body={code:"FAIL",msg:"用户未登录"}
  }
});





module.exports = router;