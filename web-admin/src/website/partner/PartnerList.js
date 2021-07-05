import React from 'react';
import { Breadcrumb,Card,Dropdown,Menu,Button,Icon,message } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import {post} from 'common/util/request';
import { Link } from 'react-router-dom'
import {time_formate} from 'common/util/validate';

export default class PartnerList extends React.Component{
    state={
      data: {
        page_num: 0,
        page_size: 10,
        list:[]
      }
    }

    componentDidMount(){
      this.load_data(this.state.page_num,this.state.page_size);
    }

    async load_data(page_num,page_size){
      const {data,code,msg}= await post('/website/list-partner',{page_num,page_size});
      if(code === 'SUCCESS'){
          this.setState({data});
      }else{
        message.error("加载失败!"+msg);
      }
    }

    getTitle() {
      return (
        <div>
          <AuthButton  auth_list={this.props.auth_list} auth_path='/website/partner/create' auth_type='button' size='small' type='primary' onClick={()=>{this.props.history.push('/website/create-partner')}}> 添加</AuthButton>
        </div>
      );
    }

    async del(id){
      const {code,msg}= await post('/website/del-partner',{id});
      if(code === 'SUCCESS'){
        message.success("删除成功!");
      }else{
         message.error("删除失败!"+msg);
      }
      this.load_data(this.state.page_num,this.state.page_size);
    }

    get_menu(data){
      return(
        <Menu>
            <AuthButton onClick={()=>{this.props.history.push(`/website/edit-partner/${data.id}`)}} auth_list={this.props.auth_list} auth_path='/website/partner/edit' auth_type='menu-item'>编辑</AuthButton>
            <AuthButton onClick={()=>{this.del(data.id)}} auth_list={this.props.auth_list} auth_path='/website/partner/del' auth_type='menu-item'>删除</AuthButton>
        </Menu>
      );
    }

    render(){
    
      const columns = [{
        title: 'id',
        dataIndex: 'id',
        key: 'id',
        width: 150,
        align: 'center'
      },{
        title: '名称',
        dataIndex: 'name',
        key: 'name',
        width: 150,
        align: 'center'
      },{
        title: '链接',
        dataIndex: 'href',
        key: 'href',
        width: 150,
        align: 'center'
      }, {
        title: '创建时间',
        dataIndex: 'create_time',
        key: 'create_time',
        width: 150,
        align: 'center',
        render:(create_time)=>{
          return time_formate(create_time)
        }
      }, {
        title: '操作',
        key: 'action',
        width: 150,
        align: 'center',
        render: (data) => (
          <div>
              <Dropdown overlay={this.get_menu(data)} placement="bottomLeft">
                    <Button>操作<Icon type="down" /></Button>
              </Dropdown> 
          </div>
        )
      }
      ];

      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-partner">合作伙伴列表</Link></Breadcrumb.Item>
          </Breadcrumb>

          <Card>
              <DefaultTable 
                title={() => { return this.getTitle() }}
                columns={columns}
                data={this.state.data}
                onChange={(pageInfo) => {this.load_data(pageInfo.currentPage,pageInfo.pageSize) }} 
              />
          </Card>
        </div>
      )
     }
}