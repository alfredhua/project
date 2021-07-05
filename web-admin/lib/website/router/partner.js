var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';

/**
 * 创建partner
 */
router.post('/save-partner',login_required,async(ctx,next)=>{
  const {id,name,href,pic_url,auth_data_code}=ctx.request.body;
  if(id){
    ctx.body=await ctx.postJson(website+'/update-partner',{id,name,href,pic_url,auth_data_code});
  }else{
    ctx.body=await ctx.postJson(website+'/create-partner',{name,href,pic_url,auth_data_code});
  }
});

/**
 * 获取详情 
 */
router.post('/get-partner',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/get-partner/${id}`,{});
});

/**
 * partner删除 
 */
router.post('/del-partner',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/del-partner/${id}`,{});
});

/**
 * partner列表
 */
router.post('/list-partner',login_required,async(ctx,next)=>{
  const {page_num,page_size}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/list-partner',{page_num,page_size});
});



module.exports = router;


