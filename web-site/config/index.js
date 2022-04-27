var data={};

function load_config(){
  if( process.env.NODE_ENV==='development'){
    data=require(`./config.dev.json`);
  }else{
    data=require(`./config.prod.json`);
  }
}

load_config();

export default function config(key, error=false){
    if(error && !data.hasOwnProperty(key)){
        throw error===true? `缺少${key}`:error;
    }
    return data[key];
}
