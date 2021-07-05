var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var auth='/admin/auth';

/**
 * 管理员列表
 */
router.post('/list-admin',login_required,async(ctx,next)=>{
   let {page_num,page_size,user_name,phone}=ctx.request.body;
   if(user_name){
    user_name=null;
   }
   if(phone){
       phone=null;
   }
   ctx.body=await ctx.postJson(auth+'/list-admin',{page_num,page_size,user_name,phone});
});

router.post('/get-admin-by-token',login_required,async(ctx,next)=>{
    const token=ctx.cookies.get("sessionId");
    if(token){
        ctx.body=await ctx.postJson(auth+'/get-admin-by-token',{}); 
    }else{
        ctx.body={code:"FAIL",msg:"token不存在"};
    }
});

router.post('/reset-password',login_required,async(ctx,next)=>{
    const {id}=ctx.request.body;
    ctx.body=await ctx.postJson(auth+'/reset-password',{id}); 
});

router.post('/set-auth-data',login_required,async(ctx,next)=>{
    const {id,auth_data_code}=ctx.request.body;
    ctx.body=await ctx.postJson(auth+'/set-auth-data',{id,auth_data_code}); 
});


router.post('/update-password',login_required,async(ctx,next)=>{
    const {old_password,new_password,confirm_password}=ctx.request.body;
    ctx.body=await ctx.postJson(auth+'/update-password',{token:ctx.token,old_password,new_password,confirm_password}); 
});


router.post('/update-admin-info',login_required,async(ctx,next)=>{
    const {email,phone}=ctx.request.body;
    ctx.body=await ctx.postJson(auth+'/update-admin-info',{token:ctx.token,email,phone}); 
});

/**
 * 创建管理员
 */
router.post('/create-admin',login_required,async(ctx,next)=>{
  const {user_name,email,phone,role_id_list,status,auth_code_list}=ctx.request.body;
  ctx.body=await ctx.postJson(auth+'/create-admin',{user_name,email,phone,role_id_list,status,auth_code_list});
});

/**
 * 管理员更新
 */
router.post('/update-admin',login_required,async(ctx,next)=>{
    const {id,user_name,password,email,phone,role_id_list,status,auth_code_list}=ctx.request.body;
    ctx.body=await ctx.postJson(auth+'/update-admin',{id,user_name,password,email,phone,role_id_list,status,auth_code_list});
});

/**
 * 管理员信息
 */
router.post('/get-admin-by-id',login_required,async(ctx,next)=>{
    const {id}=ctx.request.body;
    ctx.body=await ctx.postJson(auth+`/get-admin-by-id/${id}`,{});
});

module.exports = router;