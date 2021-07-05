var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var blog='/admin/blog';
/**
 * 类型
 */
router.post('/list-active-type',login_required,async(ctx,next)=>{
  ctx.body=await ctx.postJson(blog+'/list-active-type',{});
});

router.post('/list-all',login_required,async(ctx,next)=>{
  ctx.body=await ctx.postJson(blog+'/list-all',{});
});

router.post('/update-type-status',login_required,async(ctx,next)=>{
  const {id,status} =ctx.request.body;
  ctx.body=await ctx.postJson(blog+'/update-type-status',{id,status});
});

router.post('/list-type',login_required,async(ctx,next)=>{
  const {page_num,page_size}=ctx.request.body;
  ctx.body=await ctx.postJson(blog+'/list-type',{page_num,page_size});
});


router.post('/save-type',login_required,async(ctx,next)=>{
  const {id,name,type,introduce}=ctx.request.body;
  if(id){
    ctx.body=await ctx.postJson(blog+'/update-type',{id,name,type,introduce});
  }else{
    ctx.body=await ctx.postJson(blog+'/create-type',{name,type,introduce});
  }
});



module.exports = router;
