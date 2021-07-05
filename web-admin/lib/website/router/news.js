var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';

/**
 * 创建news
 */
router.post('/save-news',login_required,async(ctx,next)=>{
  const {id,title,source,source_url,pic,introduce,context,auth_data_code}=ctx.request.body;
  if(id){
    ctx.body=await ctx.postJson(website+'/update-news',{id,title,source,source_url,pic,introduce,context,auth_data_code});
  }else{
    ctx.body=await ctx.postJson(website+'/create-news',{title,source,source_url,pic,introduce,context,auth_data_code});
  }
});

/**
 * 获取详情 
 */
router.post('/get-news',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/get-news/${id}`,{});
});

/**
 * news删除 
 */
router.post('/del-news',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/del-news/${id}`,{});
});

router.post('/update-news-publish',login_required,async(ctx,next)=>{
  const {id,publish}=ctx.request.body;
  ctx.body=await ctx.postJson(website+`/update-news-publish`,{id,publish});
});

/**
 * news列表
 */
router.post('/list-news',login_required,async(ctx,next)=>{
  const {page_num,page_size}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/list-news',{page_num,page_size});
});



module.exports = router;


