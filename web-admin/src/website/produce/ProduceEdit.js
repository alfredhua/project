import React from 'react';
import {post} from 'common/util/request';
import { Breadcrumb,Card,message,Select } from 'antd';
import { Link } from 'react-router-dom'
import {
  Form,
  InputNumber,
  Radio,
  Button,
  Upload,
  Input
} from 'antd';
import {  Icon, Modal } from 'antd';
import {load_auth_data} from 'website/request';

var TextArea=Input.TextArea;
const Option = Select.Option;

class ProduceEdit extends React.Component{

    state={
      preview_visible:false,
      cover_visible:false,
      preview_image:null,
      load_file_list:[],
      cover_file_list:[],
      auth_data:[]
    }

    componentDidMount(){
      const {id}=this.props.match.params;
      if(id){
        this.load_data(id);
      }else{
        const { setFieldsValue } = this.props.form;
        setFieldsValue({pc_show:0,m_show:0,home_show:0})
      }
      this.load();
    }

    async load(){
      this.setState({auth_data:await load_auth_data()});
    }

    async load_data(id){
      let {data,code}=await post("/website/get-produce",{id});
      if(code==='SUCCESS'){
        const {title,home_show,ordering,file_list,introduce,cover_image}=data;
        const { setFieldsValue } = this.props.form;
        let {cover_file_list} =this.state;
        setFieldsValue({title,home_show,ordering,introduce,pic:"s",cover_pic:"s"});
        cover_file_list.push({uid:"1",url:cover_image});
        this.setState({load_file_list:JSON.parse(file_list),file_list:JSON.parse(file_list),cover_file_list,cover_image})
      }
    }

    submit=(e)=>{
      e.preventDefault();
      this.props.form.validateFields((err, values) => {
        if (!err) {
          this.save(values)
        }
      });
    }
    
    async save(form_value){
      const {file_list,cover_image}=this.state;
      const {id}=this.props.match.params;
      const result =await post("/website/save-produce",{form_value,id,files:file_list,cover_image});
      if(result && result.code === 'SUCCESS' ){
        message.success("保存成功",1,()=>{this.props.history.push(`/website/list-produce`)});
      }else{
        message.error("保存失败!"+result.msg);
      }
    }

    change_file=(file)=>{
      var file_list=[];
      if(file.file.status==='done'||file.file.status==='removed'){
        for(var i=0;i<file.fileList.length;i++){
          file_list.push({uid:file.fileList[i].uid,url:file.fileList[i].url?file.fileList[i].url:file.fileList[i].response.path});
        }
      }
      this.setState({load_file_list:file.fileList,file_list})
    }
    
    change_cover_file=({file,fileList})=>{
      if(file.status==='done'){
        this.setState({cover_image:file.response.path})
      }
      this.setState({cover_file_list:fileList})
    }

    render(){
      const { getFieldDecorator } = this.props.form;
      const formItemLayout = {
        labelCol: { span: 6 },
        wrapperCol: { span: 14 },
      };

      const uploadButton = (
        <div>
          <Icon type="plus" />
          <div className="ant-upload-text">上传</div>
        </div>
      );

      const {load_file_list,cover_file_list,preview_visible,preview_image,cover_visible,cover_image,auth_data} =this.state;
      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-produce">产品列表</Link></Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/create-produce">产品创建</Link></Breadcrumb.Item>
          </Breadcrumb>
          <Card>
          <Form {...formItemLayout} onSubmit={this.submit}>
                <Form.Item label="产品名称">
                  {getFieldDecorator('title', {
                    rules: [
                      {
                        required:true,
                        message: '请输入产品名称',
                      },
                    ],
                  })(<Input placeholder="产品名称" />)}
                </Form.Item>
              <Form.Item label="首页展示" >
                {getFieldDecorator('home_show')(
                  <Radio.Group>
                    <Radio value={0}>否</Radio>
                    <Radio value={1}>是</Radio>
                  </Radio.Group>,
                )}
              </Form.Item>

              <Form.Item label="排序">
                  {getFieldDecorator('ordering', {
                    rules: [
                      {
                        required:true,
                        message: '排序',
                      },
                    ],
                  })(<InputNumber min={0} placeholder="排序" />)}
              </Form.Item>

              <Form.Item label="数据权限" >
                {getFieldDecorator('auth_data_code')(
                 <Select style={{ width: 120 }} >
                      {auth_data&&auth_data.map((item)=>{
                        return( <Option key={item.code} value={item.code}>{item.name}</Option>)
                      })}
                  </Select>
                )}
              </Form.Item>

              <Form.Item label="封面图片">
                  {getFieldDecorator('cover_pic', {
                    rules: [
                      {
                        required:true,
                        message: '请上传封面图片',
                      },
                    ],
                  })(  <div>
                          <Upload
                           action='/api/common/upload'
                          listType="picture-card"
                          fileList={cover_file_list}
                          onPreview={(file)=>{this.setState({ cover_visible:true})}}
                          onChange={(file,item)=>{this.change_cover_file(file)}}>
                          {cover_file_list&&cover_file_list.length >= 1 ? null : uploadButton}
                        </Upload>
                        <Modal footer={null} visible={cover_visible}  onCancel={()=>{this.setState({cover_visible:false})}}>
                          <img alt="example" style={{ width: '100%' }} src={cover_image} />
                        </Modal>
                        </div>
                  )}
              </Form.Item>

              <Form.Item label="产品图片">
                  {getFieldDecorator('pic')(  <div>
                          <Upload
                           action='/api/common/upload'
                          listType="picture-card"
                          fileList={load_file_list}
                          onPreview={(file)=>{this.setState({ preview_visible:true,preview_image:file.url })}}
                          onChange={(file,item)=>{this.change_file(file)}}>
                         {load_file_list&&load_file_list.length >= 5 ? null : uploadButton}
                        </Upload>
                        <Modal footer={null} visible={preview_visible}  onCancel={()=>{this.setState({preview_visible:false})}}>
                          <img alt="example" style={{ width: '100%' }} src={preview_image} />
                        </Modal>
                        </div>
                  )}
              </Form.Item>

              <Form.Item label="简介">
                  {getFieldDecorator('introduce', {
                    rules: [
                      {
                        required:true,
                        message: '简介不能为空',
                      },
                    ],
                  })(<TextArea min={0} placeholder="简介" rows={4} />)}
              </Form.Item>



        <Form.Item wrapperCol={{ span: 12, offset: 6 }}>
          <Button type="primary" htmlType="submit">
            保存
          </Button>
        </Form.Item>
      </Form>
          </Card>
        </div>
      )
     }
}

export default Form.create()(ProduceEdit);