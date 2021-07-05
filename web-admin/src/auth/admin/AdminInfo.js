import React from 'react'
import {post} from 'common/util/request';
import { Card,Form,Input, Button,message} from 'antd';

const formItemLayout = {
  labelCol: {
    xs: { span: 10 },
    sm: { span: 5 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 16 },
  },
};
const tailFormItemLayout = {
  wrapperCol: {
    sm: {
      span: 10,
      offset: 5,
    },
  },
};

class AdminInfo extends React.Component{


  constructor(props){
    super(props);
    this.state={
      admin:{},
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

  async save(info){
   let result = await post("/auth/update-admin-info",{...info});
   if(result && result.code === 'SUCCESS' ){
      message.success("保存成功!");
    }else{
      message.success("保存失败!"+result.msg);
    }

  }

  render(){
    const { getFieldDecorator } = this.props.form;
    const {admin}=this.props;
     return(
      <div>
        <Card>
        <Form {...formItemLayout} onSubmit={this.submit}>
            
            <Form.Item label="手机号码">
              {getFieldDecorator('phone', {
                rules: [{ required: true, message: '请输入手机号码!' }],
                initialValue:admin.phone
              })(<Input style={{ width: '100%' }} />)}
            </Form.Item>

            <Form.Item label="E-mail">
              {getFieldDecorator('email', {
                rules: [
                  {type: 'email', message: '邮箱格式错误!'},
                  {required: true,message: '请输入邮箱'},
                ],
                initialValue:admin.email
              })(<Input />)}
              </Form.Item>
        
            <Form.Item {...tailFormItemLayout}>
              <Button type="primary" htmlType="submit">保存</Button>
            </Form.Item>
          </Form>
        </Card>     
      </div>
      );
    }
}

export default Form.create()(AdminInfo);