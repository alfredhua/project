import React from 'react';
import { Card,Breadcrumb,message } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import {post} from 'common/util/request';
import { Link } from 'react-router-dom'
import {time_formate} from 'common/util/validate';
const bannerType={
  "PC":"PC站首页",
  "APP":"APP首页",
  "H5":"H5站首页",
  "XXC":"小程序",
  "BLOG":"博客首页"
}

export default class BannerList extends React.Component{
    state={
      banner: {
        page_num: 0,
        page_size: 10,
        list:[]
      }
    }

    componentDidMount(){
      const {page_num,page_size}=this.state.banner;
      this.doAxios(page_num,page_size);
    }

    async doAxios(page_num,page_size){
      const {data,code,msg}= await post('/website/list-banner',{page_num,page_size});
      if(code === 'SUCCESS'){
          this.setState({banner:data});
      }else{
        message.error("加载失败!"+msg);
      }
    }

    async delete(id){
      const {code,msg}= await post('/website/delete-banner',{id});
      if(code === 'SUCCESS'){
        message.success('删除成功');
        this.doAxios(this.state.page_num,this.state.page_size);
    }else{
      message.error("删除失败!"+msg);
    }
    }
    
    change(pageInfo){
      const { current, pageSize } = pageInfo;
      this.doAxios(current - 1, pageSize);
    }


    getTitle() {
      return (
        <div>
          <AuthButton  auth_list={this.props.auth_list} auth_path='/website/banner/create' auth_type='button' size='small' type='primary' onClick={()=>{this.props.history.push('/website/create-banner')}}> 添加</AuthButton>
        </div>
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
      }, {
        title: '类型',
        dataIndex: 'type',
        key: 'type',
        width: 150,
        align: 'center',
        render:(data)=>{
          return bannerType[data];
        }
      }, {
        title: '状态',
        dataIndex: 'enable',
        key: 'enable',
        width: 150,
        align: 'center',
        render:(enable)=>{
            return enable?"关闭":"开启";
        }
      }, {
        title: '排序',
        dataIndex: 'order',
        key: 'order',
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
              <AuthButton auth_path='/website/banner/edit' auth_list={this.props.auth_list} auth_type="tag" checked={true} onClick={() => { this.props.history.push(`/website/edit-banner/${data.id}`) }} >编辑</AuthButton>
              <AuthButton auth_path='/website/banner/del' auth_list={this.props.auth_list} auth_type="tag" checked={true} onClick={() => {this.delete(data.id) }} >删除</AuthButton>
          </div>
        )
      }
      ];

      return(
        <div>
          <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-banner">banner列表</Link></Breadcrumb.Item>
          </Breadcrumb>

          <Card>
              <DefaultTable 
                title={() => { return this.getTitle() }}
                columns={columns}
                data={this.state.banner}
                onChange={(pageInfo) => { this.change(pageInfo) }} 
              />
          </Card>
        </div>
      )
     }
}