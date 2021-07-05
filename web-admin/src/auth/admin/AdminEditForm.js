import React from 'react'
import { Form,Input,Switch, Button, Checkbox,Row,Col,Select } from 'antd';
const { Option } = Select;

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

 class AdminEditForm extends React.Component{

  get_role_check_box(){
    const {role_list}=this.props;
   return role_list.map((item)=>{
      return(
        <Col span={8} key={item.id}>
           <Checkbox value={item.id}>{item.name}</Checkbox>
        </Col>
      );
    })
  }


  get_auth_data(){
    const {auth_data}=this.props;
    return auth_data&&auth_data.map(item=>{
      return <Option  key={item.code} value={item.code}>{item.name}</Option>
     });
  }

  submit=(e)=>{
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        this.props.click(values)
      }
    });
  }

  render(){
    const { getFieldDecorator } = this.props.form;
    return(
      <div>
        <div style={{color:'red'}}>
        默认密码：000000
        </div>
        <Form {...formItemLayout} onSubmit={this.submit}>
            <Form.Item label="id" style={{display:'none'}}>
                {getFieldDecorator('id', {
                })(<Input style={{ width: '100%' }} />)}
            </Form.Item>
            <Form.Item label="用户名">
              {getFieldDecorator('user_name', {
                rules: [{ required: true, message: '请输入用户名!' }],
              })(<Input style={{ width: '100%' }} autoComplete="off" />)}
            </Form.Item>
            <Form.Item label="手机号码">
              {getFieldDecorator('phone', {
                rules: [{ required: true, message: '请输入手机号码!' }],
              })(<Input style={{ width: '100%' }} />)}
            </Form.Item>

            <Form.Item label="是否激活">
              {getFieldDecorator('status', { valuePropName: 'checked' })(<Switch />)}
            </Form.Item>

            <Form.Item label="E-mail">
              {getFieldDecorator('email', {
                rules: [
                  {type: 'email', message: '邮箱格式错误!'},
                  {required: true,message: '请输入邮箱'},
                ],
              })(<Input />)}
              </Form.Item>

              <Form.Item label="角色">
              {getFieldDecorator('role_id_list', {
                rules:[
                  {required: true,message: '请选择角色'}
                ]
              })(
              <Checkbox.Group style={{ width: '100%' }}>
                  <Row>
                    {this.get_role_check_box()}
                  </Row>
              </Checkbox.Group>,
              )}
              </Form.Item>

              <Form.Item label="数据权限">
              {getFieldDecorator('auth_code_list', {
                rules:[
                  {required: true,message: '请选择数据权限'}
                ]
              })(
                <Select style={{ width: 120 }}  mode="multiple" >
                  {this.get_auth_data()}
                </Select>,
              )}
              </Form.Item>
        
            <Form.Item {...tailFormItemLayout}>
              <Button type="primary" htmlType="submit">保存</Button>
            </Form.Item>
          </Form> 
      </div>
    )
  }
}

export default (Form.create({
  mapPropsToFields(props) {
    return {
        id: Form.createFormField({value:props.id}),
        user_name: Form.createFormField({value:props.user_name}),
        password: Form.createFormField({value:props.password}),
        phone: Form.createFormField({value:props.phone}),
        status: Form.createFormField({value:props.status===1?true:false}),
        email: Form.createFormField({value:props.email}),
        role_id_list:Form.createFormField({value:props.role_id_list}),
        auth_code_list:Form.createFormField({value:props.auth_code_list}),

    };
  }
})(AdminEditForm))