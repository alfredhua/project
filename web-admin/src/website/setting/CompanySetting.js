import React from 'react';
import {post} from 'common/util/request';
import { Breadcrumb,Card,message,Button } from 'antd';
import { Link } from 'react-router-dom';
import DefaultBraftEditor from 'common/components/editor/DefaultBraftEditor';

export default class CompanySetting extends React.Component{

    state={
        content:null,
        auth_list:[]
    }

    componentDidMount(){
        this.load_data();
    }

    async load_data(){
      const {type}=this.props.location.state;
      const {data,code,msg}= await post('/website/get-setting-detail',{type});
      const result= await post('/website/list-children-setting',{type:'ABOUT_US'});
      if(code === 'SUCCESS'){
          if(data&&data.content){
            this.setState({content:data.content,auth_list:result.data});
          }else{
            this.setState({auth_list:result.data}); 
          }
      }else{
        message.error("加载失败!"+msg);
      }
    }

    get_title(type){
      const {auth_list} =this.state;
      let name;
      for(var i=0;i<auth_list.length;i++){
         if(type===auth_list[i].type){
           name=auth_list[i].name;
           break;
         }
      }
     return name;
    }
    async save(){
        const { content }=this.state;
        const {type}=this.props.location.state;
        if(content===null||content===''){
            message.error("请填写内容！");
            return;
        }
        const { code,msg }= await post('/website/update-setting-detail',{content:content.toHTML(),type});
        if(code === 'SUCCESS'){
            message.success("保存成功",1,()=>{this.props.history.push(`/website/setting`)});
        }else{
          message.error("加载失败!"+msg);
        }
    }

    render(){
       const { content } =this.state;
      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/setting">网站设置</Link></Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/setting">{this.get_title(this.props.location.state.type)}</Link></Breadcrumb.Item>
          </Breadcrumb>
          <Card>
                <div style={{marginTop:30}} >{this.get_title(this.props.location.state.type)}介绍：</div>

                <DefaultBraftEditor context={content} onChange={(content)=>{this.setState({content})}} />

                <Button style={{marginTop:30,marginLeft:30}} onClick={()=>{this.save()}} >
                     保存
                </Button>
          </Card>
        </div>
      )
     }
}