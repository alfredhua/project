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
      this.load_data(this.state.data.page_num,this.state.data.page_size);
    }

    async load_data(page_num,page_size){
      const {data,code,msg}= await post('/website/list-news',{page_num,page_size});
      if(code === 'SUCCESS'){
          this.setState({data});
      }else{
        message.error("加载失败!"+msg);
      }
    }

    getTitle() {
      return (
        <div>
          <AuthButton  auth_list={this.props.auth_list} auth_path='/website/news/create' auth_type='button' size='small' type='primary' onClick={()=>{this.props.history.push('/website/create-news')}}> 添加</AuthButton>
        </div>
      );
    }

    async del(id){
      const {code,msg}= await post('/website/del-news',{id});
      if(code === 'SUCCESS'){
        message.success("删除成功!");
      }else{
         message.error("删除失败!"+msg);
      }
      this.load_data(this.state.page_num,this.state.page_size);
    }

    async publish(id,publish){
      const {code}= await post('/website/update-news-publish',{id,publish});
      if(code === 'SUCCESS'){
        message.success(publish===1?"发布成功!":"撤回成功");
      }else{
        message.error(publish===1?"发布失败!":"撤回失败"); ;
      }
      this.load_data(this.state.page_num,this.state.page_size);
    }

    get_menu(data){
      return(
        <Menu>
            <AuthButton onClick={()=>{this.props.history.push(`/website/edit-news/${data.id}`)}} auth_list={this.props.auth_list} auth_path='/website/news/edit' auth_type='menu-item'>编辑</AuthButton>
            <AuthButton onClick={()=>{this.del(data.id)}} auth_list={this.props.auth_list} auth_path='/website/news/del' auth_type='menu-item'>删除</AuthButton>
            <AuthButton onClick={()=>{this.publish(data.id,1)}} auth_list={this.props.auth_list} auth_path='/website/news/publish' auth_type='menu-item'>发布</AuthButton>
            <AuthButton onClick={()=>{this.publish(data.id,3)}} auth_list={this.props.auth_list} auth_path='/website/news/recall' auth_type='menu-item'>撤回</AuthButton>

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
        key: 'title',
        width: 150,
        align: 'center'
      },{
        title: '来源',
        dataIndex: 'source',
        key: 'source',
        width: 150,
        align: 'center'
      },{
        title: '来源链接',
        dataIndex: 'source_url',
        key: 'source_url',
        width: 150,
        align: 'center'
      }, {
        title: '是否发布',
        dataIndex: 'publish',
        key: 'publish',
        width: 150,
        align: 'center',
        render:(publish)=>{
          if(publish===0){
            return '未发布';
          }else if(publish===1){
            return '已发布';
          }else{
            return '已撤回';
          }
        }
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
        title: '发布时间',
        dataIndex: 'publish_time',
        key: 'publish_time',
        width: 150,
        align: 'center',
        render:(publish_time)=>{
          return time_formate(publish_time)
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
              <Breadcrumb.Item><Link to="/website/list-news">新闻列表</Link></Breadcrumb.Item>
          </Breadcrumb>

          <Card>
              <DefaultTable 
                title={() => { return this.getTitle() }}
                columns={columns}
                data={this.state.data}
                onChange={(pageInfo) => { this.change(pageInfo) }} 
              />
          </Card>
        </div>
      )
     }
}