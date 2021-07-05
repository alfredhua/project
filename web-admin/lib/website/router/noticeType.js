var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';
/**
 * 创建文章字典
 */
router.post('/create-notice-type',login_required,async(ctx,next)=>{
  const {name,type,auth_data_code}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/create-notice-type',{name,type,auth_data_code});
});

/**
 * 文章字典列表
 */
router.post('/list-notice-type-by-page',login_required,async(ctx,next)=>{
  const { pageNum, pageSize }=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/list-notice-type-by-page',{pageNum, pageSize});
});

/**
 * 文章字典列表
 */
router.post('/update-notice-type',login_required,async(ctx,next)=>{
  const {id,name,type,auth_data_code}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/update-notice-type',{id,name,type,auth_data_code});
});

/**
 * 文章字典列表
 */
router.post('/update-notice-type-status',login_required,async(ctx,next)=>{
  const {id,status}=ctx.request.body;
  ctx.body=await ctx.postJson(website+`/update-notice-type-status`,{id,status});
});

/**
 * 所有文章类型
 */
router.post('/list-all-notice-type',login_required,async(ctx,next)=>{
  ctx.body=await ctx.postJson(website+`/list-all-notice-type`,{});
});
/**
 * 所有激活的类型
 */
router.post('/list-active-notice-type',login_required,async(ctx,next)=>{
  ctx.body=await ctx.postJson(website+`/list-active-notice-type`,{});
});



module.exports = router;
