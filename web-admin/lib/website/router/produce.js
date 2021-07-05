var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var website='/admin/website';

/**
 * 创建
 */
router.post('/save-produce',login_required,async(ctx,next)=>{
  const {id,form_value,files,cover_image}=ctx.request.body;
  const {title,home_show,ordering,introduce,auth_data_code}=form_value;
  if(id){
    ctx.body=await ctx.postJson(website+'/update-produce',{id,title,pc_show:1,m_show:1,file_list:JSON.stringify(files),home_show,ordering,introduce,cover_image,auth_data_code});
  }else{
    ctx.body=await ctx.postJson(website+'/create-produce',{title,pc_show:1,m_show:1,file_list:JSON.stringify(files),home_show,ordering,introduce,cover_image,auth_data_code});
  }
});

router.post('/list-produce',login_required,async(ctx,next)=>{
    const {page_num,page_size}=ctx.request.body;
    ctx.body=await ctx.postJson(website+'/list-produce',{page_num,page_size});
});

router.post('/del-produce',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/del-produce/${id}`,{});
});

router.post('/get-produce',login_required,async(ctx,next)=>{
  const {id}=ctx.request.body;
  ctx.body=await ctx.postString(website+`/get-produce/${id}`,{});
});

/**
 * 更新
 */
router.post('/update-produce-detail',login_required,async(ctx,next)=>{
  const {context,id}=ctx.request.body;
  ctx.body=await ctx.postJson(website+'/update-produce-detail',{id,context});
});




module.exports = router;


