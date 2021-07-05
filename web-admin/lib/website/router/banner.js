var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';

/**
 * 创建banner
 */
router.post('/create-banner',login_required,async(ctx,next)=>{
  const {name,href,type,order,enable,url,auth_data_code}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/create-banner',{name,href,type,order,enable,url,auth_data_code});
});

/**
 * 创建banner
 */
router.post('/update-banner',login_required,async(ctx,next)=>{
  const {id,name,href,type,order,enable,url,auth_data_code}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/update-banner',{id,name,href,type,order,enable,url,auth_data_code});
});

/**
 * 获取详情 
 */
router.post('/get-banner',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+'/get-banner',{id});
});

/**
 * banner删除 
 */
router.post('/delete-banner',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/delete-banner/${id}`,{});
});

/**
 * banner列表
 */
router.post('/list-banner',login_required,async(ctx,next)=>{
  const {page_num,page_size}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/list-banner',{page_num,page_size});
});



module.exports = router;


