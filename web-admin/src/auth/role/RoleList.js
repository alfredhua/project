import React from 'react'
import { Breadcrumb,Card } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import { Link } from 'react-router-dom'
import moment  from 'moment';
import {post} from 'common/util/request';

export default class RoleList extends React.Component{
   
  constructor(props){
    super(props);
    this.state={
      role:{},
      page_size:10,
      page_num:1
    }
  }

  componentDidMount(){
    this.load_data(this.state.page_num,this.state.page_size);
  }

  async load_data(page_num,page_size){
    const {code,data} =await post("/auth/list-role",{ page_num,page_size});
    if(code === 'SUCCESS' ){
      this.setState({role:data});
    }
  }

  async page_to(pageInfo){
    const { current,pageSize }=pageInfo;
    this.load_data(current,pageSize)
  }

  render(){
      const columns = [{
        title: 'ID号',
        dataIndex: 'id',
        key: 'id',
      }, {
        title: '角色名称',
        dataIndex: 'name',
        key: 'name',
      }, {
        title: '备注',
        dataIndex: 'comment',
        key: 'comment',
      }, {
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
            <AuthButton auth_list={this.props.auth_list} auth_type="tag" auth_path={'/auth/role/edit'} onClick={()=>{this.props.history.push(`/auth/edit-role/${data.id}`)}}>编辑</AuthButton>
          </div>
        )
      }];
      const {role}=this.state;
      return(
        <div>
         <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>权限管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/auth/list-role">角色列表</Link></Breadcrumb.Item>
          </Breadcrumb>
          <Card>
              <AuthButton auth_list={this.props.auth_list} auth_type="button" auth_path={'/auth/role/create'} onClick={()=>{this.props.history.push(`/auth/create-role`)}}>创建</AuthButton>
              <DefaultTable  columns={columns}  data={role} onChange={(pageInfo) => { this.page_to(pageInfo) }} />  
          </Card>
        </div>
      )
    }
}
