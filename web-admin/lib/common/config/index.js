import path from 'path';
import fs from 'fs';

const cwd=process.cwd();

function dir_exists(dir){
  try{
    fs.accessSync(dir, fs.constants.R_OK);
    return fs.statSync(dir).isDirectory();
  }catch(e){}
}
const config_path=path.resolve(cwd,'./');

if(!config_path){
  throw new Error('config path not found');
}
var data={};
export const dev = process.env.NODE_ENV==='dev';
function load_config(path){
  if(dev){
    data=require(`${path}/config.dev.json`);
  }else{
    data=require(`${path}/config.prod.json`);
  }
}
load_config(config_path);


export default function config(key, error=false){
    if(error && !data.hasOwnProperty(key)){
        throw error===true? `缺少${key}`:error;
    }
    return data[key];
}
