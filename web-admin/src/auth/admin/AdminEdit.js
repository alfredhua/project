import React from 'react'
import { Card,Breadcrumb,message} from 'antd';
import { Link } from 'react-router-dom'
import AdminEditForm from './AdminEditForm';
import {post} from 'common/util/request';

export default class AdminEdit extends React.Component{

  constructor(props){
    super(props);
    this.state={
      admin:{},
      role_list:[],
      auth_data:[]
    }
  }

  componentDidMount(){
    const {id}=this.props.match.params;
    this.load_data(id);
    this.load_auth_data();
  }

  async load_data(id){
    let admin_result;
    const role_list_result = await post("/auth/list-all-use-role",{});
    if(id){
      admin_result = await post("/auth/get-admin-by-id",{id});
      admin_result.data.role_id_list=JSON.parse(admin_result.data.role_id_list);
      this.setState({admin:admin_result.data,role_list:role_list_result.data});
    }else{
      this.setState({role_list:role_list_result.data});
    }
  }

  async load_auth_data(){
    const {code,data} = await post("/auth/list-all-auth-data",{});
    if(code === 'SUCCESS' ){
      this.setState({auth_data:data});
    }
  }

  async save(admin){
    let result;
    admin.role_id_list=JSON.stringify(admin.role_id_list);
    admin.status=admin.status?1:0;
    if(admin.id){
       result=await post("/auth/update-admin",{...admin});
    }else{
       result= await post("/auth/create-admin",{...admin});
    }
    if(result && result.code === 'SUCCESS' ){
      message.success("保存成功",1,()=>{this.props.history.push(`/auth/list-admin`)});
    }else{
      message.error("保存失败!"+admin.msg);
    }
  }


  render(){
     return(
      <div>
        <Breadcrumb style={{ margin: '16px 0' }}>
            <Breadcrumb.Item>权限管理</Breadcrumb.Item>
            <Breadcrumb.Item> <Link to="/auth/list-admin">管理员列表</Link></Breadcrumb.Item>
            <Breadcrumb.Item>管理员编辑</Breadcrumb.Item>
        </Breadcrumb>
        <Card>
            <AdminEditForm {...this.state.admin} role_list={this.state.role_list} auth_data={this.state.auth_data} click={(values)=>{this.save(values)}} /> 
        </Card>     
      </div>
      );
    }
}