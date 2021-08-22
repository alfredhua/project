import React from 'react';
import { Breadcrumb,Card,message,Dropdown,Button,Menu,Icon } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import {post} from 'common/util/request';
import { Link } from 'react-router-dom'
import {time_formate} from 'common/util/validate';

export default class ArticleList extends React.Component{
    state={
      article: {
        page_num: 1,
        page_size: 10,
        list:[]
      }
    }

    componentDidMount(){
      this.load_data();
    }

    async load_data(page_num,page_size){
      const {data,code,msg}= await post('/blog/list-article',{page_num,page_size});
      if(code === 'SUCCESS'){
          this.setState({article:data});
      }else{
        message.error("加载失败!"+msg);
      }
    }

    async delete(id){
      const {code,msg}= await post('/blog/delete-article',{id});
      if(code === 'SUCCESS'){
        message.success('删除成功');
        this.load_data(this.state.page_num,this.state.page_size);
    }else{
      message.error("删除失败!"+msg);
     }
  }
    
    page_to(pageInfo){
      const { current, pageSize } = pageInfo;
      this.load_data(current, pageSize);
    }

    async change_status(id,status){
      const {code,msg}= await post('/blog/update-article-status',{id,status});
      if(code === 'SUCCESS'){
        message.success('修改成功');
        this.load_data(this.state.page_num,this.state.page_size);
      }else{
        message.error("修改失败!"+msg);
     }
    }

    get_title() {
      return (
        <div>
          <AuthButton  auth_list={this.props.auth_list} auth_path='/blog/article/create' auth_type='button' size='small' type='primary' onClick={()=>{this.props.history.push('/blog/create-article')}}> 添加</AuthButton>
        </div>
      );
    }
    get_menu(data){
      return(
        <Menu>
            <AuthButton auth_list={this.props.auth_list} onClick={()=>{this.props.history.push(`/blog/edit-article/${data.id}`)}}  auth_path='/blog/article/edit' auth_type='menu-item'>编辑</AuthButton>
            <AuthButton auth_list={this.props.auth_list} onClick={()=>{this.change_status(data.id,1)}}  auth_path='/blog/article/publish' auth_type='menu-item'>发布</AuthButton>
            <AuthButton auth_list={this.props.auth_list} onClick={()=>{this.change_status(data.id,2)}}  auth_path='/blog/article/recall' auth_type='menu-item'>撤回</AuthButton>
            <AuthButton auth_list={this.props.auth_list} onClick={()=>{this.delete(data.id)}}  auth_path='/blog/article/delete' auth_type='menu-item'>删除</AuthButton>
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
        title: '标题',
        dataIndex: 'title',
        width: 150,
        align: 'center'
      }, {
        title: '类型',
        dataIndex: 'type',
        align: 'center'
      }, {
        title: '状态',
        dataIndex: 'status',
        align: 'center',
        render:(status)=>{
            if(status===0){
              return "待发布";
            }else if(status===1){
              return "发布";
            }else if(status===2){
              return "已撤回";
            }
           
        }
      },{
        title: '是否转载',
        dataIndex: 'reprint',
        align: 'center',
        render:(reprint)=>{
            return reprint?"是":"否";
        }
      }, {
        title: '点击数',
        dataIndex: 'click_count',
        key: 'click_count',
        align: 'center'
      },{
        title: '赞赏数',
        dataIndex: 'like_count',
        key: 'like_count',
        align: 'center'
      }, {
        title: '创建时间',
        dataIndex: 'create_time',
        key: 'create_time',
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
          <Dropdown overlay={this.get_menu(data)} placement="bottomLeft">
              <Button>操作<Icon type="down" /></Button>
          </Dropdown> 
        )
      }
      ];
      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>博客管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/blog/list-article">博文列表</Link></Breadcrumb.Item>
          </Breadcrumb>

          <Card>
              <DefaultTable 
                title={() => { return this.get_title() }}
                columns={columns}
                data={this.state.article}
                onChange={(pageInfo) => { this.page_to(pageInfo) }} 
              />
          </Card>
        </div>
      )
     }
}