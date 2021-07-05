import React from 'react'
import { Card,Form,Input,message,Button} from 'antd';
import {post} from 'common/util/request';


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


 class AdminPasswordEdit extends React.Component{

  constructor(props){
    super(props)
    this.state={
      confirm_password:null,
      password:null,
      old_password:null
    }
  }
  validate_to_next_password = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm_password'], { force: true });
    }
    callback();
  };

  compare_to_first_password = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('new_password')) {
      callback('俩次密码不一致！');
    } else {
      callback();
    }
  };


  handle_confirm_blur = e => {
    const value = e.target.value;
    this.setState({ confirm_password: this.state.confirm_password || !!value });
  };

  submit=(e)=>{
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        this.update_password(values)
      }
    });
  }

  async update_password(values){
    const result=await post("/auth/update-password",{...values});
    if(result.code==='SUCCESS'){
      message.success("修改成功！");
      this.props.form.resetFields();
    }else{
      message.success("修改失败！");
    }
  } 

  render(){

    const { getFieldDecorator } = this.props.form;

    return(
      <div>
        <Card>
          <Form {...formItemLayout} onSubmit={this.submit}>
            <Form.Item label="旧密码">
                  {getFieldDecorator('old_password', {
                    rules: [{ required: true, message: '请输入当前密码!' }],
                  })(<Input.Password />)}
             </Form.Item>

             <Form.Item label="新密码">
                  {getFieldDecorator('new_password', {
                    rules: [{ required: true, message: '请输入新密码!' },
                            {validator: this.validate_to_next_password}],
                  })(<Input.Password />)}
             </Form.Item>

             <Form.Item label="确认密码">
                  {getFieldDecorator('confirm_password', {
                    rules: [{ required: true, message: '请再次输入新密码!' },
                            {validator: this.compare_to_first_password}],
                  })(<Input.Password onBlur={this.handle_confirm_blur}/>)}
             </Form.Item>

             <Form.Item {...tailFormItemLayout}>
              <Button type="primary" htmlType="submit">保存</Button>
            </Form.Item>

          </Form> 
        </Card>
      </div>
    )
  }
}

export default Form.create()(AdminPasswordEdit);