var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';

router.post('/list-setting',login_required,async(ctx,next)=>{
  const {page_num,page_size}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/list-setting',{page_num,page_size});
});

router.post('/update-setting-status',login_required,async(ctx,next)=>{
  const {id,status}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/update-setting-status',{id,status});
});


router.post('/update-setting-detail',login_required,async(ctx,next)=>{
  const {type,content,url,introduce}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/update-setting-detail',{type,content,url,introduce});
});

router.post('/get-setting-detail',login_required,async(ctx,next)=>{
  const {type}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/get-setting-detail/${type}`,{});
});


router.post('/list-children-setting',login_required,async(ctx,next)=>{
  const {type}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/list-children-setting/${type}`,{});
});


module.exports = router;


