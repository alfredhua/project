import React from 'react';
import {post} from 'common/util/request';
import { Breadcrumb,Card,Select,Radio,Button,message,Upload, Modal, Icon} from 'antd';
import LongInput from 'common/components/input/LongInput';
import DefaultInputNumber from 'common/components/input/DefaultInputNumber';
import { Link } from 'react-router-dom'
const RadioGroup = Radio.Group;
const Option=Select.Option;


export default class BannerEditForm extends React.Component{

  state={
    fileList:[],
    previewVisible: false,
    previewImage: '',
    enable:1,
    order:null,
    auth_data:[],
    auth_data_code:null
  }
  componentDidMount(){
    this.load_data();
    this.load_auth_data();
  }

  async load_auth_data(){
    const {code,data} = await post("/auth/list-all-auth-data",{});
    if(code === 'SUCCESS' ){
      this.setState({auth_data:data});
    }
  }

  async load_data(){
    const {id}=this.props.match.params;
    if(id){
      const {code,msg,data}=await post(`/website/get-banner`,{id});
      if(code==='SUCCESS'){
        let fileList=[];
        fileList.push({uid:data.id,url:data.url});
        const {type,id,name,href,enable,url,order,auth_data_code}=data;
        this.setState({type,id,name,href,enable,order,url,fileList,auth_data_code});
      }else{
        message.error('获取失败!'+msg);
      }
    }
  }

  handleChange = ({ fileList }) => {
    let url=null;
    if(fileList[0]&&fileList[0].status&&fileList[0].status==='done'){
      url=fileList[0].response.path;
    }
    this.setState({fileList,url})
  }

  handleSelectChange=(type)=> this.setState({type})

  changeRadio = (e) => {
    this.setState({
      enable: e.target.value,
    });
  }

  handlePreview = () => {
    this.setState({
      previewVisible: true,
    });
  }

  save=async()=>{
    const {id,name,type,href,enable,order,url,auth_data_code}=this.state;
    if(!name){
      message.error('名称为空');
      return;
    }
    if(!type){
      message.error('类型为空');
      return;
    }
    if(!url){
      message.error('图片为空');
      return;
    }
    if(!auth_data_code){
      message.error('请选择数据权限');
      return;
    }
    if(id){
      const {code,msg} =await post('/website/update-banner',{id,name,type,href,enable,order,url,auth_data_code})
      if(code === 'SUCCESS'){
        message.success("保存成功",1,()=>{this.props.history.push(`/website/list-banner`)});
      }else{
        message.error('保存失败!'+msg);
      }
    }else{
      const {code,msg} =await post('/website/create-banner',{name,type,href,enable,order,url,auth_data_code})
      if(code === 'SUCCESS'){
        message.success("保存成功",1,()=>{this.props.history.push(`/website/list-banner`)});
      }else{
        message.error('保存失败!'+msg);
      }
    }
  }
  


  render(){
    const { fileList,previewVisible,type,auth_data }=this.state;
    const uploadButton = (
      <div>
        <Icon type='plus' />
        <div className='ant-upload-text'>上传</div>
      </div>
    );

    return(
      <div>
           <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-banner">banner列表</Link></Breadcrumb.Item>
              <Breadcrumb.Item>banner编辑</Breadcrumb.Item>
          </Breadcrumb>
      
      <Card>
       
            <LongInput label='名称' value={this.state.name} onChange={(e)=>{this.setState({name:e.target.value})}}></LongInput>

            <LongInput label='跳转地址'  value={this.state.href} onChange={(e)=>{this.setState({href:e.target.value})}} ></LongInput>

          <div style={{marginTop:20}}>
            <div style={{float:'left',marginTop:'5px',width:'200px',height:'20px',textAlign:'right'}}>
              <label style={{textAlign:'right'}}>类型：</label>
            </div>
            <Select style={{width:'100px'}} placeholder='类型' value={type} onChange={this.handleSelectChange}>
                  <Option value='PC'>PC</Option>
                  <Option value='APP'>APP</Option>
                  <Option value='H5'>手机站</Option>
                  <Option value='XXC'>小程序</Option>
                  <Option value='BLOG'>博客首页</Option>
            </Select>
          </div>

          <div style={{marginTop:20}}>
            <div style={{float:'left',width:'200px',height:'20px',textAlign:'right'}}>
              <label style={{textAlign:'right'}}>状态：</label>
            </div>
            <RadioGroup style={{marginLeft:'10px'}}  name='radiogroup' onChange={this.changeRadio} value={this.state.enable}  >
                <Radio value={1}>关闭</Radio>
                <Radio value={0}>开启</Radio>
            </RadioGroup>
          </div>

          <DefaultInputNumber label='排序' min={0} max={100}  value={this.state.order} onChange={(value)=>{this.setState({order:value})}}></DefaultInputNumber>

           <div style={{marginTop:20}}>
                <div style={{float:'left',width:'200px',height:'20px',textAlign:'right'}}> <label>图片：</label></div>
                 <div style={{float:'left'}}>
                 <Upload 
                      action='/api/common/upload'
                      listType='picture-card'
                      fileList={fileList}
                      onPreview={()=>{this.handlePreview()}}
                      onChange={(fileList)=>{this.handleChange(fileList)}} >
                      {fileList.length >= 1 ? null : uploadButton}
                  </Upload>
                  <Modal visible={previewVisible} footer={null} onCancel={()=>{ this.setState({ previewVisible: false })}}>
                      <img alt='example' style={{ width: '100%' }} src={this.state.url} />
                  </Modal>
                 </div>
                 <div style={{clear:'both'}}></div>
             </div>

            <div style={{marginTop:20}}>
                <div style={{float:'left',marginTop:'5px',width:'200px',height:'20px',textAlign:'right'}}>
                   <label style={{textAlign:'right'}}>数据权限：</label>
                </div>
                <Select style={{ width: 120 }} onChange={(value)=>{this.setState({auth_data_code:value})}} value={this.state.auth_data_code}>
                    {auth_data&&auth_data.map(item=>{
                      return <Option  key={item.code} value={item.code}>{item.name}</Option>
                    })}
                </Select>
              </div>

            <div style={{marginLeft:350,marginTop:20}}>
              <Button type='primary' onClick={()=>{this.save()}}>提交</Button>
             </div>
     </Card>
     </div>
    )
  }
}
