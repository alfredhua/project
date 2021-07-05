var router = require('koa-router')();
import { login_required } from '@server/common/middle/auth';

var fs = require('fs');

router.post('/authority-list',login_required,async(ctx,next)=>{
   var tree_data= load_auth_by_path(`${__dirname}/authority`);
   ctx.body={code:"SUCCESS",msg:"获取成功",data:tree_data};
});

function load_auth_by_path(path) {
    var tree_date=[];
    path = path.endsWith('/') ? path.substr(0, path.length - 1) : path;
    var index = read_json(`${path}/index.json`);
    if (index) {
        for (var key in index) {
            var data = read_json(`${path}/${key}.json`);
            if (data) {
                tree_date.push(auth_data_norm(data,key));
            }
        }
    }
    return tree_date;
}

function auth_data_norm(data,path) {
    if(data){
        var key =data['key'];
        if(key){
           data['key']=`${path}/${data.key}`;
        }else{
           data['key']=`/${path}`;
        }
        var children=data['children'];
        if(children){
            for(var child in children){
               auth_data_norm(children[child],data['key']);    
            }
        }
    }
    return data;
}

function read_json(file_path) {
    try {
        fs.accessSync(file_path, fs.R_OK);
        var data_str = fs.readFileSync(file_path, "utf8");
        return JSON.parse(data_str);
    } catch (e) {
    }
  }

module.exports = router;