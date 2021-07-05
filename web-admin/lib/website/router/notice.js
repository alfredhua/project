var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';
/**
 * 创建notice
 */
router.post('/create-notice',login_required,async(ctx,next)=>{
  const {notice}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/create-notice',{...notice});
});

/**
 * 创建notice
 */
router.post('/update-notice',login_required,async(ctx,next)=>{
  const {notice}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/update-notice',{...notice});
});

/**
 * 获取详情 
 */
router.post('/get-notice',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/get-notice/${id}`,{});
});

/**
 * notice删除 
 */
router.post('/del-notice',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/del-notice/${id}`,{});
});

/**
 * notice发布
 */
router.post('/publish-notice',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/publish-notice/${id}`,{});
});

/**
 * notice列表
 */
router.post('/list-notice',login_required,async(ctx,next)=>{
  const {page_num,page_size,type}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/list-notice',{page_num,page_size,type:type===1?null:type});
});



module.exports = router;


