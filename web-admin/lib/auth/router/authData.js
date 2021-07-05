var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var auth='/admin/auth';

router.post('/list-auth-data',login_required,async(ctx,next)=>{
   let {page_num,page_size}=ctx.request.body;
   ctx.body=await ctx.postJson(auth+'/list-auth-data',{page_num,page_size});
});

router.post('/save-auth-data',login_required,async(ctx,next)=>{
  const {name,id,code}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+'/save-auth-data',{name,id,code});
});


router.post('/list-all-auth-data',login_required,async(ctx,next)=>{
  ctx.body=await ctx.postJson(auth+'/list-auth-data-all',{});
});



module.exports = router;