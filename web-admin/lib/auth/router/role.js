var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';
var auth='/admin/auth';


/**
 * 创建角色
 */
router.post('/create-role',login_required,async(ctx,next)=>{
  const {name,comment,auth_list}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+'/create-role',{name,comment,auth_list:JSON.stringify(auth_list)});
});

/**
 * 角色列表
 */
router.post('/list-role',login_required,async(ctx,next)=>{
  const {page_num,page_size}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+'/list-role',{page_num,page_size});
});



/**
 * 根据id获取角色
 */
router.post('/get-role-by-id',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+`/get-role-by-id/${id}`,{});
});

/**
 * 获取所有可用的角色
 */
router.post('/list-all-use-role',login_required,async(ctx,next)=>{
  ctx.body=await ctx.postJson(auth+'/list-all-use-role',{});
});

/**
 * 角色更新
 */
router.post('/update-role',login_required,async(ctx,next)=>{
  const {id,name,comment,auth_list}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+'/update-role',{id,name,comment,auth_list:JSON.stringify(auth_list)});
});


/**
 * 角色禁用,启用
 */
router.post('/update-role-status',async(ctx,next)=>{
  const {id,status}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+'/update-role-status',{id,status});
});

module.exports = router;
