var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var blog='/admin/blog';
/**
 * 创建文章字典
 */
router.post('/save-article',login_required,async(ctx,next)=>{
  const {id,title,type,context,pic_url,reprint,introduce,content_type}=ctx.request.body;
  if(id){
    ctx.body=await ctx.postJson(blog+'/update-article',{id,title,type,context,pic_url,reprint,introduce,content_type});
  }else{
    ctx.body=await ctx.postJson(blog+'/create-article',{title,type,context,pic_url,reprint,introduce,content_type});
  }
});


router.post('/update-article-status',login_required,async(ctx,next)=>{
  const {id,status}=ctx.request.body;
  ctx.body=await ctx.postJson(blog+'/update-article-status',{id,status});
});

router.post('/delete-article',login_required,async(ctx,next)=>{
  const { id }=ctx.request.body;
  ctx.body=await ctx.postString(blog+`/del-article/${id}`,{});
});

router.post('/get-article',login_required,async(ctx,next)=>{
  const { id }=ctx.request.body;
  ctx.body=await ctx.postString(blog+`/get-article/${id}`,{});
});

/**
 * 文章字典列表
 */
router.post('/list-article',login_required,async(ctx,next)=>{
  const { page_num,page_size }=ctx.request.body;
  ctx.body=await ctx.postJson(blog+'/list-article',{page_num,page_size});
});


module.exports = router;
