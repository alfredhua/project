import React from 'react'
import { Breadcrumb,Card, message,Modal } from 'antd'
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import { Link } from 'react-router-dom'
import moment  from 'moment';
import {post} from 'common/util/request';

export default class AdminList extends React.Component{

    constructor(props){
      super(props);
      this.state={
        admin:{},
        page_size:10,
        page_num:1,
        reset_visible:false,
      }
    }

    componentDidMount(){
      this.load_data(this.state.page_num,this.state.page_size);
      this.load_auth_data();
    }

    async load_data(page_num,page_size){
      const {code,data} = await post("/auth/list-admin",{page_num,page_size});
      if(code === 'SUCCESS' ){
          this.setState({admin:data});
      }
    }

    async load_auth_data(){
      const {code,data} = await post("/auth/list-all-auth-data",{});
      if(code === 'SUCCESS' ){
        this.setState({auth_data:data});
    }
    }

    async page_to(pageInfo){
      const { current,pageSize }=pageInfo;
      this.load_data(current,pageSize)
    }

    async reset_password(){
      const {id} =this.state;
      const {code,msg} = await post("/auth/reset-password",{id});
      if(code === 'SUCCESS' ){
        message.success("重置密码成功！")
        this.setState({reset_visible:false});
      }else{
        message.error("重置密码失败！"+msg);
        this.setState({reset_visible:false});
      }
    }

    async set_auth_data(){
      const {id,auth_data_code} =this.state;
      const {code,msg} = await post("/auth/set-auth-data",{id,auth_data_code});
      if(code === 'SUCCESS' ){
        message.success("设置成功！")
      }else{
        message.error("设置失败"+msg);
      }
      this.setState({data_visible:false});
    }


    render(){
      const columns = [{
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
      },{
        title: '用户名',
        dataIndex: 'user_name',
        key: 'user_name',
      }, {
        title: '邮箱',
        dataIndex: 'email',
        key: 'email',
      },{
        title: '手机号',
        dataIndex: 'phone',
        key: 'phone',
      }, {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        render:(status)=>{
          return status===1?"激活":"冻结"
        }
      },{
        title: '创建时间',
        dataIndex: 'create_time',
        key: 'create_time',
        render:(create_time)=>{
          return(<div>{moment(create_time).format('YYYY-MM-DD HH:mm:ss')}</div>)
        }
      },{
        title: '操作',
        key: 'action',
        align: 'center',
        render: (data) => (
          <div>
            <AuthButton auth_list={this.props.auth_list} auth_type="tag" auth_path={'/auth/admin/edit'} onClick={()=>{this.props.history.push(`/auth/edit-admin/${data.id}`)}}>编辑</AuthButton>
            <AuthButton auth_list={this.props.auth_list} auth_type="tag" auth_path={'/auth/admin/reset'} onClick={()=>{this.setState({reset_visible:true,id:data.id})}}>重置密码</AuthButton>
            <AuthButton auth_list={this.props.auth_list} auth_type="tag" auth_path={'/auth/admin/set-auth-data'} onClick={()=>{this.setState({data_visible:true,id:data.id})}}>修改数据权限</AuthButton>
          </div>
        )
      }];
      const {admin}=this.state;
      return(
        <div>
            <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>权限管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/auth/list-admin">管理员列表</Link></Breadcrumb.Item>
          </Breadcrumb>

          <Card>
             <AuthButton auth_list={this.props.auth_list} auth_path={'/auth/admin/create'} auth_type="button" 
                         onClick={()=>{this.props.history.push(`/auth/create-admin`)}}>创建</AuthButton>
             <DefaultTable columns={columns}  data={admin} onChange={(pageInfo) => { this.page_to(pageInfo) }} />  
          </Card>
          <Modal title="重置密码" visible={this.state.reset_visible}
               onOk={()=>{this.reset_password()}} onCancel={()=>{this.setState({reset_visible:false})}} >
              <p>确定重置密码？</p>
          </Modal>

          <Modal title="设置数据权限" visible={this.state.data_visible}
               onOk={()=>{this.set_auth_data()}} onCancel={()=>{this.setState({data_visible:false,auth_data_code:null})}} >
              <p>  
           
            </p>
          </Modal>

        </div>
      )
    }
}