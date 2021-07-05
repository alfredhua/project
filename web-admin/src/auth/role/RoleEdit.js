import React from 'react'
import { Breadcrumb,Card,Button,message,Checkbox } from 'antd';
import LongInput from 'common/components/input/LongInput';
import { Link } from 'react-router-dom'
import {post} from 'common/util/request';

export default class RoleEdit extends React.Component{
   
  constructor(props){
    super(props);
    this.state={
      info:{
        name:null,
        comment:null,
        auth_list:[],
      },
      authority_list:[]
    }
  }
 
  componentDidMount(){
    const {id}=this.props.match.params;
    this.load_data(id);
  }

  async load_data(id){
    let {info}=this.state;
    if(id){
       info = await post("/auth/get-role-by-id",{id});
      if(info&&info.code==='SUCCESS'){
        info=info.data;
        info.auth_list=JSON.parse(info.auth_list);
      }
    }
    const authority_result = await post("/auth/authority-list",{});
    this.setState({info,authority_list:authority_result.data})
  }


  change(item,value){
    let {info}=this.state;
    info[item]=value;
    this.setState({info});
  }

  async save(){
    const {id,name,comment,auth_list}=this.state.info;
    if(!name&&name.trim()===''){
      message.error("角色名称不能为空");
      return;
    }
    if(!auth_list){
      message.error("权限不能为空!");
      return;
    }
    let result;
    if(id){
      result=await post("/auth/update-role",{id,name,comment,auth_list});
    }else{
        result=await post("/auth/create-role",{name,comment,auth_list});
    }
    if(result&&result.code === 'SUCCESS' ){
      message.success("保存成功",1,()=>{this.props.history.push(`/auth/list-role`)});
    }else{
      message.success("保存失败!"+result.msg);
    }
  };

  check_second_auth =(item)=>{
    const {info} =this.state;
    const check_auth_list =this.get_key(item);
    const index=info.auth_list.indexOf(item.key); 
    const {authority_list}=this.state;
    // const parentKey =  item.key
    const parent_key =  item.key.substring(0,item.key.lastIndexOf('/'));
    if (index > -1) { 
      //存在则删除
      info.auth_list.splice(index, 1);
      for(var i=0;i<check_auth_list.length;i++){
        const children_index=info.auth_list.indexOf(check_auth_list[i]); 
        if(children_index>-1){
          info.auth_list.splice(children_index, 1); 
        }
      }
      //判断是否删除父节点
     let parent_item;
     for(var j=0;j<authority_list.length;j++){
       if(parent_key===authority_list[j].key){
        parent_item=authority_list[j];
        break;
       }
     }
     const current_parent_key=this.get_key(parent_item);
     var flag=false;
     for(var k=0;k<current_parent_key.length;k++){
        const children_index=info.auth_list.indexOf(current_parent_key[k]); 
        if(children_index>-1){
          flag=true;
          break;
        }
      }
      if(!flag){
        //删除父节点
        const parent_index=info.auth_list.indexOf(parent_key); 
          if(parent_index>-1){
            info.auth_list.splice(parent_index, 1); 
        }
      }

    }else{
      //添加
      info.auth_list.push(item.key);
      for(var f=0;f<check_auth_list.length;f++){
        const children_index=info.auth_list.indexOf(check_auth_list[f]); 
        if(children_index === -1){
          info.auth_list.push(check_auth_list[f]); 
        }
      }
      //一定添加父节点
      const parent_index=info.auth_list.indexOf(parent_key); 
      if(parent_index===-1){
        info.auth_list.push(parent_key); 
      }

    }
    this.setState(info);
  }

  del_first_auth=(first_parent_key,authority_list,auth_list)=>{
    let current_parent_key=[];
    for(var j=0;j<authority_list.length;j++){
      if(authority_list[j].key===first_parent_key){
        current_parent_key.push.apply(current_parent_key,this.get_key(authority_list[j]));
      }
    }
    for(var k=0;k<current_parent_key.length;k++){
      const children_index=auth_list.indexOf(current_parent_key[k]); 
      if(children_index>-1){
        //存在
        return false;
      }
    }
    return true;
  }

  del_second_auth=(first_parent_key,second_parent_key,authority_list,auth_list)=>{
    let current_parent_key=[];
    for(var i=0;i<authority_list.length;i++){
      if( authority_list[i].key === first_parent_key){
        for(var j=0;j<authority_list[i].children.length;j++){
          if(authority_list[i].children[j].key===second_parent_key){
            current_parent_key.push.apply(current_parent_key,this.get_key(authority_list[i].children[j]));
          }
        }
      }
    }
    for(var k=0;k<current_parent_key.length;k++){
      const children_index=auth_list.indexOf(current_parent_key[k]); 
      if(children_index > -1){
        //存在
        return false;
      }
    }
    return true;
  }

  check_three_auth =(item) =>{
    const {info} =this.state;
    const index=info.auth_list.indexOf(item.key); 
    const second_parent_key =  item.key.substring(0,item.key.lastIndexOf('/'));
    const first_parent_key =  second_parent_key.substring(0,second_parent_key.lastIndexOf('/'));
    const {authority_list}=this.state;
    if(index > -1){
      //删除
      info.auth_list.splice(index, 1); 

      if(this.del_second_auth(first_parent_key,second_parent_key,authority_list,info.auth_list)){
        const second_index=info.auth_list.indexOf(second_parent_key); 
        if(second_index > -1){
         info.auth_list.splice(second_index, 1); 
        }
      }

      if(this.del_first_auth(first_parent_key,authority_list,info.auth_list)){
         const first_parent_index=info.auth_list.indexOf(first_parent_key); 
         if(first_parent_index>-1){
             info.auth_list.splice(first_parent_index, 1); 
        }
      }
    }else{
      //添加
      info.auth_list.push(item.key); 
      const second_index=info.auth_list.indexOf(second_parent_key); 
      if(second_index === -1){
        info.auth_list.push(second_parent_key); 
      }

      const first_index=info.auth_list.indexOf(first_parent_key); 
      if(first_index === -1){
        info.auth_list.push(first_parent_key); 
      }

    }
    this.setState(info);
  }

  check_first_auth = (item) =>{
     const {info} =this.state;
     //获取所有的子节点
     const check_auth_list =this.get_key(item);
     const index=info.auth_list.indexOf(item.key); 
     if (index > -1) { 
      info.auth_list.splice(index, 1); 
      //删除
      for(var i=0;i<check_auth_list.length;i++){
        const children_index=info.auth_list.indexOf(check_auth_list[i]); 
        if(children_index>-1){
          info.auth_list.splice(children_index, 1); 
        }
      }
     }else{
      info.auth_list.push(item.key);
      //添加
      for(var k=0;k<check_auth_list.length;k++){
        const children_index=info.auth_list.indexOf(check_auth_list[k]); 
        if(children_index === -1){
          info.auth_list.push(check_auth_list[k]); 
        }
      }
     }
     this.setState(info);
   }


   get_key =(item)=>{
     let auth_list=[];
     auth_list= this.get_children_key(item,auth_list);
     return auth_list;
   }

   get_children_key =(item,auth_list) =>{
    if(item.children){
      item.children.map((childrenItem)=>{
         auth_list.push(childrenItem.key);
         return this.get_children_key(childrenItem, auth_list);
      });
    }
    return auth_list;
   }




    contains = (key) =>{
      const {info} =this.state;
      const index=info.auth_list.indexOf(key); 
      if(index>-1){
        return true;
      }
      return false;
    }

    get_first_auth = (data) =>{
     return data&&data.map((item)=>{
          return(
            <div key={item.key} style={{width:'100%'}}>
                <div style={{float:'left',width:'20%',borderTop:'1px solid #e8e8e8'}}>
                   <Checkbox onChange={()=>{this.check_first_auth(item)}} checked={this.contains(item.key)} style={{marginTop:10,marginLeft:20}}>{item.title}</Checkbox>
                </div>
                <div style={{float:'left',width:'80%'}}>{this.get_second_auth(item.children)} </div>
            </div>
          )
      }); 
    }
    get_second_auth = (data) =>{
      return data&&data.map(item=>{
           return(
                <div  key={item.key} style={{minHeight:50,borderTop:'1px solid #e8e8e8'}}>
                  <div style={{float:'left',width:'20%',minWidth:120,minHeight:50,padding:'10px 0px',borderRight:'1px solid #e8e8e8'}}>
                    <Checkbox onChange={()=>{this.check_second_auth(item)}}  checked={this.contains(item.key)}>{item.title}</Checkbox>
                    </div>
                  <div style={{float:'left',width:'80%'}}>{this.get_three_auth(item.children)} </div> 
                </div>
           )
       }); 
     }

     get_three_auth = (data) =>{
      return data&&data.map(item=>{
           return(<Checkbox  onChange={()=>{this.check_three_auth(item)}} checked={this.contains(item.key)} key={item.key} style={{marginLeft:10,marginTop:10}}>{item.title}</Checkbox>)
       }); 
     }


    render(){
      const {info}=this.state;
      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item></Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/auth/list-role">角色列表</Link></Breadcrumb.Item>
              <Breadcrumb.Item>角色编辑</Breadcrumb.Item>
          </Breadcrumb>
          
          <Card>

              <LongInput placeholder="角色名称" label="角色名称" value={info.name} onChange={(e)=>{this.change('name',e.target.value)}}/>

              <LongInput placeholder="备注" label="备注" value={info.comment} onChange={(e)=>{this.change('comment',e.target.value)}}/>

              <Card style={{marginTop:20}}>
                  {this.get_first_auth(this.state.authority_list)}
              </Card>

              {/* <Tree
                  checkable 
                  autoExpandParent={true}
                  onCheck={this.onCheck}
                  checkedKeys={info.auth_list}
                  style={{marginLeft:200,marginTop:5}} >
                 {this.renderTreeNodes(this.state.authority_list)}
              </Tree> */}

              <Button style={{marginLeft:200,marginTop:20}} type="primary" size="small" onClick={()=>{this.save()}} >保存</Button>
              
          </Card>
        </div>
      )
    }
}