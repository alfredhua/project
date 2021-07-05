import React from 'react'
import { Breadcrumb,Card,Modal,Input } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import { Link } from 'react-router-dom'
import moment  from 'moment';
import {post} from 'common/util/request';

export default class DataList extends React.Component{
   
  constructor(props){
    super(props)
    this.state={
      auth_data:{},
      page_size:10,
      page_num:1,
      visible:false,
      name:null,
      code:null
    }
  }

  componentDidMount(){
      this.load_data(this.state.page_num,this.state.page_size)
  }

  async load_data(page_num,page_size){
    const {code,data} =await post("/auth/list-auth-data",{ page_num,page_size});
    if(code === 'SUCCESS' ){
      this.setState({auth_data:data});
    }
  }

  async save(){
    const {id,code,name,page_num,page_size}=this.state;
    const result =await post("/auth/save-auth-data",{id,code,name});
    if( result.code === 'SUCCESS' ){
        this.setState({visible:false,code:null,name:null});
    }
    this.load_data(page_num,page_size);
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
        title: '角色编码',
        dataIndex: 'code',
        key: 'code',
      }, {
        title: '数据角色名称',
        dataIndex: 'name',
        key: 'name',
      }, {
        title: '创建时间',
        dataIndex: 'create_time',
        key: 'create_time',
        render:(create_time)=>{
          return(<div>{moment(create_time).format('YYYY-MM-DD HH:mm:ss')}</div>)
        }
      }];
      const {auth_data}=this.state;
      return(
        <div>
         <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>权限管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/auth/list-data">数据权限列表</Link></Breadcrumb.Item>
          </Breadcrumb>
          <Card>
              <AuthButton auth_list={this.props.auth_list} auth_type="button" auth_path={'/auth/data/create'} onClick={()=>{this.setState({visible:true})}}>创建</AuthButton>
              <DefaultTable  columns={columns}  data={auth_data} onChange={(pageInfo) => { this.page_to(pageInfo) }} />  
          </Card>

          <Modal title="创建"
            visible={this.state.visible}
            onOk={()=>{this.save()}}
            onCancel={()=>{this.setState({visible:false})}}>
            <p><Input style={{ width: '100%' }}  placeholder="编码" onChange={(e)=>{this.setState({code:e.target.value})}}/></p>
            <p><Input style={{ width: '100%' }}  placeholder="名称" onChange={(e)=>{this.setState({name:e.target.value})}} /></p>
        </Modal>

        </div>
      )
    }
}
