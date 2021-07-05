import React from 'react';
import { Breadcrumb,Card,Dropdown,Menu,Button,Icon,message } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import {post} from 'common/util/request';
import { Link } from 'react-router-dom'
import {time_formate} from 'common/util/validate';

export default class ProduceList extends React.Component{
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
      const {data,code,msg}= await post('/website/list-produce',{page_num,page_size});
      if(code === 'SUCCESS'){
          this.setState({data});
      }else{
        message.error("加载失败!"+msg);
      }
    }

    getTitle() {
      return (
        <div>
          <AuthButton  auth_list={this.props.auth_list} auth_path='/website/produce/create' auth_type='button' size='small' type='primary' onClick={()=>{this.props.history.push('/website/create-produce')}}> 添加</AuthButton>
        </div>
      );
    }

    async del(id){
      const {code,msg}= await post('/website/del-produce',{id});
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
            <AuthButton onClick={()=>{this.props.history.push(`/website/edit-produce/${data.id}`)}} auth_list={this.props.auth_list} auth_path='/website/produce/edit' auth_type='menu-item'>编辑</AuthButton>
            <AuthButton onClick={()=>{this.del(data.id)}} auth_list={this.props.auth_list} auth_path='/website/produce/del' auth_type='menu-item'>删除</AuthButton>
            <AuthButton onClick={()=>{this.props.history.push({pathname:`/website/set-produce-detail/${data.id}`,state:{title:data.title}})}} auth_list={this.props.auth_list} auth_path='/website/produce/set-detail' auth_type='menu-item'>编辑文章内容</AuthButton>
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
        dataIndex: 'title',
        key: 'title',
        width: 150,
        align: 'center'
      },{
        title: 'pc展示',
        dataIndex: 'pc_show',
        key: 'pc_show',
        width: 150,
        align: 'center',
        render:(pc_show)=>{
          return pc_show===0?"关闭":"开启";
        }
      }, {
        title: '手机站展示',
        dataIndex: 'm_show',
        key: 'm_show',
        width: 150,
        align: 'center',
        render:(m_show)=>{
          return m_show===0?"关闭":"开启";
        }
       },{
          title: '首页展示',
          dataIndex: 'home_show',
          key: 'home_show',
          width: 150,
          align: 'center',
          render:(pc_show)=>{
            return pc_show===0?"关闭":"开启";
          }
       },{
        title: '排序',
        dataIndex: 'ordering',
        key: 'ordering',
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
              <Breadcrumb.Item><Link to="/website/list-produce">产品列表</Link></Breadcrumb.Item>
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