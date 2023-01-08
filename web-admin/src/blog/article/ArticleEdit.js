import React from 'react';
import BraftEditor from 'braft-editor'
import 'braft-editor/dist/index.css';
import {post} from 'common/util/request';
import { Link } from 'react-router-dom'
import { Breadcrumb,Input,Radio,Upload, Modal,Button, message,Select,Card,Icon} from 'antd';
import DefaultBraftEditor from 'common/components/editor/DefaultBraftEditor';
import MarkdownEdit from 'common/components/markdown/MarkdownEdit';
const TextArea=Input.TextArea;
const Option = Select.Option;
const RadioGroup = Radio.Group;
const { confirm } = Modal;

export default class ArticleEdit extends React.Component{

  state = {
    context: '',
    type_list:[],
    file_list:[],
    title:null,type:null,pic_url:null,
    reprint:0,
    content_type:1,
    image_path:null
  }

  async componentDidMount () {
    this.load_data();
  }

  async load_data(){
    const {id}=this.props.match.params;
    let {content_type,title,type,pic_url,introduce,context,reprint,file_list}=this.state;
    if(id){
      const {code,data,msg}=await post('/blog/get-article',{id});
      if(code === 'SUCCESS'){
        title=data.title;
        type=data.type;
        pic_url=data.pic_url;
        reprint=data.reprint;
        introduce=data.introduce;
        content_type=data.content_type;
        file_list.push({uid: '-1',url:pic_url, status: 'done'});
        context=content_type===0?BraftEditor.createEditorState(data.context):data.context;
      }else{
        message.error("加载失败！"+msg);
      }
    }
    const {code,data,msg}=await post('/blog/list-active-type',{});
    if(code === 'SUCCESS'){
      this.setState({type_list:data,title,file_list,type,pic_url,reprint,context,introduce,content_type});
    }else{
      message.error("加载失败！"+msg);
    }
  }

  save =async()=>{
    let {pic_url,title,type,context,reprint,introduce,content_type} =this.state;
    const {id}=this.props.match.params;
    let result=await  post('/blog/save-article',{id,pic_url,introduce,title,reprint,type,context:content_type===0?context.toHTML():context,content_type});
    const {code,msg} =result;
    if(code === 'SUCCESS'){
      message.success("保存成功!",1,()=>{this.props.history.push("/blog/list-article")});
    }else{
      message.error("保存失败！"+msg);
    }
  }

  handle_file = ({file, fileList }) => {
    let pic_url=null;
  if(file.status==='done'){
      pic_url=file.response.path;
    }
    this.setState({file_list:fileList,pic_url})
  }

  handle_file_path= ({file, fileList }) => {
    let image_path=null;
    if(file.status==='done'){
      image_path=file.response.path;
    }
    this.setState({image_path})
  }

  change_content_type =(value) =>{
  confirm({
title: '提示',
content: '修改编辑类型会清空当前内容，是否确定？',
onOk: ()=>{this.setState({content_type:value,context:''})},
      onCancel:()=>{},
    });
  }

  render(){
    const { context,type_list,file_list,title,introduce,type,preview_visible,reprint,content_type,image_path} = this.state;
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
          <Breadcrumb.Item><Link to="/blog/list-article">博文列表</Link></Breadcrumb.Item>
          <Breadcrumb.Item>博文编辑</Breadcrumb.Item>
        </Breadcrumb>
        <Card>
          <Input addonBefore="标题：" onChange={(e)=>{this.setState({title:e.target.value})}} value={title} />

          <div style={{marginTop:'10px'}}>
            <div style={{width: '80px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
              float:'left',textAlign:'center',margin:'0 auto',
              backgroundColor:'#fafafa',display:'block'}} >文章类型:</div>
            <Select style={{ width: 120 }} onChange={(value)=>{this.setState({type:value})}} value={type} >
              {type_list&&type_list.map((item)=>{
                return( <Option key={item.id} value={item.type}>{item.name}</Option>)
              })}
            </Select>
          </div>

  <div style={{marginTop:'10px'}}>
  <div style={{width: '80px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
    float:'left',textAlign:'center',margin:'0 auto',
  backgroundColor:'#fafafa',display:'block'}} >是否转载:</div>
  <RadioGroup style={{marginTop:'5px',marginLeft:'10px'}}  onChange={(e)=>{this.setState({reprint:e.target.value})}}   value={reprint}>
  <Radio value={0}>否</Radio>
              <Radio value={1}>是</Radio>
            </RadioGroup> 
          </div>

          <div style={{marginTop:'10px'}}>
            <div style={{width: '80px',height:'32px',lineHeight:'32px',border:'1px solid #d9d9d9',
                            float:'left',textAlign:'center',margin:'0 auto',
                              backgroundColor:'#fafafa',display:'block'}} >编辑类型:</div>
                <RadioGroup style={{marginTop:'5px',marginLeft:'10px'}}  onChange={(e)=>{this.change_content_type(e.target.value)}}   value={content_type}>
                    <Radio value={0}>HTML</Radio>
                    <Radio value={1}>Markdown</Radio>
                </RadioGroup> 
              </div>

              <div style={{marginTop:'20px',height:'120px'}}>
               <span style={{float:'left'}}>图片(1024x600)：</span>
                  <Upload 
                      action='/api/common/upload'
                      listType='picture-card'
                      fileList={file_list}
                      onPreview={()=>{this.setState({preview_visible:true})}}
                      onChange={(file,fileList)=>{this.handle_file(file,fileList)}} >
                      {file_list.length >= 1 ? null : uploadButton}
                  </Upload>
                  <Modal visible={preview_visible} footer={null} onCancel={()=>{ this.setState({ preview_visible: false })}}>
                      <img alt='example' style={{ width: '100%' }} src={this.state.pic_url} />
                  </Modal>
                <div style={{clear:'both'}}></div>
             </div>


              <div style={{marginTop:'10px'}}>
                 <div style={{width: '80px',}} >简介:</div>
                <TextArea rows={4} value={introduce} onChange={(e)=>{this.setState({introduce:e.target.value})}} />
              </div>

              <div style={{marginTop:'10px'}}>
                <Upload action='/api/common/upload' onChange={(file,fileList)=>{this.handle_file_path(file,fileList)}} >
                  <Button type='primary' >文件路径</Button>
                </Upload>
                <div>{image_path}</div>
              </div>
			<div>
			</div>

              <div style={{clear:'both',marginTop:'20px'}}></div>
                {content_type===0?
                <DefaultBraftEditor context={context} onChange={(context)=>{ this.setState({context})}} />:
                <MarkdownEdit text={context} onChange={(context)=>{this.setState({context})}}></MarkdownEdit>}
              <div style={{marginTop:10}}>
                <Button type='primary' onClick={this.save.bind(this)}>保存</Button>
              </div>
          </Card>
       </div>
    )
  }
}
