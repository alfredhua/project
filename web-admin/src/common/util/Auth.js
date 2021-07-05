export function isok(auth_list,auth_path){
    if(auth_path ==='/'){
      return true;
    }
    if(auth_path){
      let flag=false;
      if(auth_list&&auth_list.length>0){
        for(var i=0;i<auth_list.length;i++){
          if(auth_list[i]===auth_path){
            flag=true;
            break;
          }
        }
      }
      return flag;
    }else{
        return false;
    }
}

export function menu_tree_auth(auth_list,auth_path){
  if(auth_path ==='/'){
    return true;
  }
  if(auth_path){
    let flag=false;
    if(auth_list&&auth_list.length>0){
      for(var i=0;i<auth_list.length;i++){
        if(auth_list[i].substring(0,auth_path.length)===auth_path){
          flag=true;
          break;
        }
      }
    }
    return flag;
  }else{
      return false;
  }
}
