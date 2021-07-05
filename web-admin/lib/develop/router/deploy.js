var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';
import config from '../../common/config';

var develop='/admin/develop';

router.post('/get-sql',login_required,async(ctx,next)=>{
  let server=config('admin-server');
  ctx.body={ url : server+"/druid/sql.html"};
});


router.post('/get-swagger',login_required,async(ctx,next)=>{
  let server=config('admin-server');
  ctx.body={ url : server+"/swagger-ui.html"};
});

router.post('/list-deploy',login_required,async(ctx,next)=>{
  const {page_num,page_size,name}=ctx.request.body;
  ctx.body=await ctx.postJson(develop+'/list-deploy',{page_num,page_size,name});
});


router.post('/update-deploy',login_required,async(ctx,next)=>{
  const { name,name_value,description }=ctx.request.body;
  ctx.body=await ctx.postJson(develop+'/update-deploy',{ name,name_value,description });
});


router.post('/delete-deploy',login_required,async(ctx,next)=>{
  const { name }=ctx.request.body;
  ctx.body=await ctx.postString(develop+`/delete-deploy/${name}`,{  });
});


module.exports = router;


