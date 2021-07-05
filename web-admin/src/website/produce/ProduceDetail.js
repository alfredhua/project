import React from 'react';
// 引入编辑器样式
import {post} from 'common/util/request';
import { Breadcrumb,Input,Button, message,Card } from 'antd';
import { Link } from 'react-router-dom';
import DefaultBraftEditor from 'common/components/editor/DefaultBraftEditor';

export default class ArticleEdit extends React.Component{

    state = {
        context: null,
        title:null,
        id:null
    }

    async componentDidMount () {
      const {id}=this.props.match.params;
      this.load_data(id);
    }

    async load_data(id){
      const {data,code,msg}= await post('/website/get-produce',{id});
      if(code === 'SUCCESS'){
          let context=null;
           if(data&&data.context){
            context=data.context;
           }
          this.setState({context,id});
      }else{
         message.error("加载失败!"+msg);
      }
    }

    async save(){
      const {context}=this.state;
      let  result= await post('/website/update-produce-detail',{id:this.props.match.params.id,context:context.toHTML()});
      if(result && result.code === 'SUCCESS' ){
        message.success("保存成功",1,()=>{this.props.history.push(`/website/list-produce`)});
      }else{
        message.error("保存失败!"+result.msg);
      }

    }

  render(){
    const {context}=this.state;
    return(
      <div >
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-produce">产品列表</Link></Breadcrumb.Item>
              <Breadcrumb.Item>编辑产品内容</Breadcrumb.Item>
          </Breadcrumb>
        <Card>
              <Input addonBefore="标题：" disabled value={this.props.history.location.state.title} />
              <div style={{clear:'both',marginTop:'20px'}}></div>
              <DefaultBraftEditor context={context} onChange={(context)=>{this.setState({context})}} />
              <div>
                <Button type='primary' onClick={()=>{this.save()}}>保存</Button>
              </div>
          </Card>
       </div>
    )
  }
}