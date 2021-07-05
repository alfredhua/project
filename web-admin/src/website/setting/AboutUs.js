import React from 'react';
import {post} from 'common/util/request';
import { Breadcrumb,Card,message,Input,Upload,Icon,Button } from 'antd';
import { Link } from 'react-router-dom';
import DefaultBraftEditor from 'common/components/editor/DefaultBraftEditor';

var TextArea=Input.TextArea;

export default class AboutUs extends React.Component{

    state={
        content:null,
        introduce:null,
        url:null,
        file_list:[],
    }

    componentDidMount(){
        this.load_data();
    }

    async load_data(){
      const {data,code,msg}= await post('/website/get-setting-detail',{type:'ABOUT_US'});
      if(code === 'SUCCESS'){
          let file_list=[];
          if(data&&data.url){
            file_list.push({
                uid: '1',
                name: '首页视频',
                url: data.url,
                thumbUrl: data.url,
              });
             this.setState({...data,file_list,is_upload:true});
          }
      }else{
        message.error("加载失败!"+msg);
      }
    }

    async save(){
        const { content,introduce,url }=this.state;
        if(introduce===null||introduce===''){
            message.error("请填写简介！");
            return;
        }
        if(url===null||url===''){
            message.error("请上传文件！");
            return;
        }
        if(content===null||content===''){
            message.error("请填写内容！");
            return;
        }
        const { code,msg }= await post('/website/update-setting-detail',{content:content.toHTML(),introduce,url,type:'ABOUT_US'});
        if(code === 'SUCCESS'){
            message.success("保存成功",1,()=>{this.props.history.push(`/website/setting`)});
        }else{
          message.error("加载失败!"+msg);
        }
    }

    save_url=({file,fileList})=>{
        if(file.status==='done'){
            this.setState({url:file.response.path,is_upload:true});
        }
    }

    remove=()=>{
        this.setState({url:null,is_upload:false});
    }
    render(){
       const { content,introduce,is_upload,url } =this.state;
       const props = {
        accept:'video/*',
        action: '/api/common/upload',
        listType: 'picture',
        onChange:this.save_url,
        defaultFileList:[{
            uid: '1',
            name: 'video.mp4',
            status: 'done',
            url: url,
          }],
        onRemove:this.remove
      };
      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/setting">网站设置</Link></Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/setting/aboutus">关于我们</Link></Breadcrumb.Item>
          </Breadcrumb>
          <Card>
                <TextArea min={0} value={introduce} placeholder="首页简介" rows={4} onChange={(e)=>{this.setState({introduce:e.target.value})}} /> 

                <Upload {...props}>
                    <span>首页视频(图片)</span>
                    <Button disabled={is_upload} style={{marginTop:30,marginLeft:30}}>
                        <Icon type="upload" /> Upload
                    </Button>
                 </Upload>

                <div style={{marginTop:30}} >详情介绍：</div>
                <DefaultBraftEditor context={content} onChange={(content)=>{this.setState({content})}} />

                <Button style={{marginTop:30,marginLeft:30}} onClick={()=>{this.save()}} >
                        保存
                </Button>
          </Card>
        </div>
      )
     }
}