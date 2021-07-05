import React from 'react';
import { Breadcrumb,Input,Radio,InputNumber,Button, message,Select,Card } from 'antd';
import BraftEditor from 'braft-editor'
// 引入编辑器样式
import {post} from 'common/util/request';
import { Link } from 'react-router-dom';
import 'braft-editor/dist/index.css';
import {load_auth_data} from 'website/request';

const RadioGroup = Radio.Group;
const Option = Select.Option;


export default class NoticeEdit extends React.Component{

    state = {
        editor_state: BraftEditor.createEditorState(null),
        notice_type_list:[],
        notice:{publish:0},
        auth_data:null
    }

    async componentDidMount () {
        this.load_data();
        this.setState({auth_data:await load_auth_data()});
    }

    async load_data(){
        const {id}=this.props.match.params;
        let {notice,editor_state}=this.state;
        if(id){
          const {code,data,msg}=await post('/website/get-notice',{id});
          if(code === 'SUCCESS'){
            notice=data;
            editor_state=BraftEditor.createEditorState(notice.context)
          }else{
            message.error("加载失败！"+msg);
          }
        }
        const {code,data,msg}=await post('/website/list-active-notice-type',{});
        if(code === 'SUCCESS'){
          this.setState({notice_type_list:data,notice,editor_state});
        }else{
          message.error("加载失败！"+msg);
        }
    }

    save =async()=>{
      let {notice} =this.state;
      notice['context']=this.state.editor_state.toHTML();
      const {id}=this.props.match.params;
      let result;
      if(id){
         result=await  post('/website/update-notice',{notice});
      }else{  
        result=await  post('/website/create-notice',{notice});
      }
      const {code,msg} =result;
      if(code === 'SUCCESS'){
         message.success("保存成功!",1,()=>{this.props.history.push("/website/list-notice")});
      }else{
        message.error("保存失败！"+msg);
      }
    }
   
    handleChange(value,event){
      let { notice } =this.state;
      notice['type']=value;
      this.setState({notice})
    }

    change(attribute,value){
      const {id}=this.props.match.params;
      if( attribute === "publish" && !id){
         message.error("首次创建时，默认未发布状态!");
         return;
      }
      let { notice } =this.state;
      notice[attribute]=value;
      this.setState({notice});
    }

  render(){
    const { editor_state,notice_type_list,notice,auth_data} = this.state;
    console.log(auth_data);
    return(
      <div >
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-notice">文章列表</Link></Breadcrumb.Item>
              <Breadcrumb.Item>文章编辑</Breadcrumb.Item>
          </Breadcrumb>
        <Card>
              <Input addonBefore="标题：" onChange={(e)=>{this.change("title",e.target.value)}} value={notice.title} />
              <div style={{marginTop:'10px'}}>
                 <div style={{width: '80px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
                            float:'left',textAlign:'center',margin:'0 auto',
                              backgroundColor:'#fafafa',display:'block'}} >类型:</div>
                <Select style={{ width: 120 }} onChange={this.handleChange.bind(this)} value={notice.type} >
                    {notice_type_list&&notice_type_list.map((item)=>{
                      return( <Option key={item.id} value={item.type}>{item.name}</Option>)
                    })}
                </Select>
              </div>

              <div style={{marginTop:'10px'}}>
                <div style={{width: '80px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
                            float:'left',textAlign:'center',margin:'0 auto',
                              backgroundColor:'#fafafa',display:'block'}} >是否发布:</div>
                <RadioGroup style={{marginTop:'5px',marginLeft:'10px'}}  onChange={(e)=>{this.change("publish",e.target.value)}}   value={notice.publish}>
                  <Radio value={1}>是</Radio>
                  <Radio value={0}>否</Radio>
                </RadioGroup> 
              </div>

              <div style={{marginTop:'20px'}}>
              <div style={{width: '80px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
                            float:'left',textAlign:'center',margin:'0 auto',
                              backgroundColor:'#fafafa',display:'block'}} >排序:</div>
                 <InputNumber min={1} max={10} disabled={this.state.disabled} value={notice.ordering} onChange={(value)=>{this.change("ordering",value)}} />
              </div>

              <div style={{marginTop:'20px'}}>
                 <div style={{width: '100px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
                            float:'left',textAlign:'center',margin:'0 auto',
                              backgroundColor:'#fafafa',display:'block'}} >数据权限类型:</div>
                <Select style={{ width: 120 }} onChange={(value)=>{this.change("auth_data_code",value)}} value={notice.auth_data_code} >
                    {auth_data&&auth_data.map((item)=>{
                      return( <Option key={item.code} value={item.code}>{item.name}</Option>)
                    })}
                </Select>
              </div>

              <div style={{clear:'both',marginTop:'20px'}}></div>
              <BraftEditor style={{border:'1 auto'}} value={editor_state} onChange={(editor_state)=>{ this.setState({ editor_state })}} />

              <div>
                <Button type='primary' onClick={this.save.bind(this)}>保存</Button>
              </div>
          </Card>
       </div>
    )
  }
}