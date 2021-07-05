import React from 'react';
import { Breadcrumb,Card,Select } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import {post} from 'common/util/request';
import { message,Modal } from 'antd';
import { Link } from 'react-router-dom'
const Option = Select.Option;

export  default class NoticeList extends React.Component{

   state={
      data: {
        page_num: 0,
        page_size: 10,
        list:[],
        publish_modal:false,
        type:1,
        id:'',
      },
      type:1,
      operation:''
    }
  componentDidMount(){
    this.load_data(this.state.page_num,this.state.page_size,this.state.type);
  }

  async load_data(page_num,page_size,type){
    const result= await post('/website/list-notice',{page_num,page_size,type});
    const notice_type_result=await post('/website/list-all-notice-type',{});
    if( result.code === 'SUCCESS'){
      this.setState({data:result.data,notice_type_list:notice_type_result.data,type});
    }else{
      message.error('加载失败!'+result.msg);
    }
  }

  get_title() {
    const {notice_type_list,type} =this.state;
    return (
      <div>
          <Select style={{ width: 120 }} defaultValue={1} onChange={(value,event)=>{this.setState({type:value})}} value={type} >
                <Option key={1} value={1}>{"全部"}</Option>
                {notice_type_list&&notice_type_list.map((item)=>{
                      return( <Option key={item.id} value={item.type}>{item.name}</Option>)
                })}
         </Select>

         <AuthButton style={{marginLeft:10}} auth_list={this.props.auth_list} auth_path='/website/notice/list' 
          auth_type='button' size='small' type='primary' onClick={()=>{this.load_data(this.state.page_num,this.state.page_size,this.state.type)}}> 搜索</AuthButton>

        <AuthButton style={{marginLeft:10}} auth_list={this.props.auth_list} auth_path='/website/notice/create' 
          auth_type='button' size='small' type='primary' onClick={()=>{this.props.history.push(`/website/create-notice`)}}> 添加</AuthButton>
      </div>
    );
  }

  delete= async(id)=>{
    const {code,msg}= await post('/website/del-notice',{id});
    if(code === 'SUCCESS'){
      message.info('删除成功!');
    }else{
      message.error('删除失败!'+msg);
    }
    this.load_data(this.state.page_num,this.state.page_size);
  }

  publish= async(id)=>{
    const {code,msg}= await post('/website/publish-notice',{id});
    if(code === 'SUCCESS'){
      message.success('发布成功!');
      this.load_data(this.state.page_num,this.state.page_size,this.state.type);
      this.setState({publish_modal:false})
    }else{
      message.error('发布失败!'+msg);
    }
  }

  handle_ok = () => {
    const {operation,id}=this.state;
    if(operation==='PUBLISH'){
      this.publish(id);
    }else{
      this.delete(id);
    }
  }

  get_notice_type=(notice_type)=>{
    const {notice_type_list}=this.state;
    var name;
    notice_type_list.forEach((item)=>{
      if(item.type===notice_type ){
        return name=item.name; 
      }
    });
    return name;
  }

  render(){
    const columns = [{
      title: '标题',
      dataIndex: 'title',
      key: 'title',
      width: 150,
      align: 'center'
    },{
      title: '类型',
      dataIndex: 'type',
      key: 'type',
      width: 150,
      align: 'center',
      render:(type)=>{
        return this.get_notice_type(type);
      }
    },{
      title: '状态',
      dataIndex: 'publish',
      key: 'publish',
      width: 50,
      align: 'center',
      render:(publish)=>{
          return publish?'开启':'关闭';
      }
    },{
      title: '浏览次数',
      dataIndex: 'click_count',
      key: 'click_count',
      width: 100,
      align: 'center'
    },{
      title: '排序',
      dataIndex: 'ordering',
      key: 'ordering',
      width: 50,
      align: 'center'
    },{
      title: '创建时间',
      dataIndex: 'create_time',
      key: 'create_time',
      width: 200,
      align: 'center'
    },{
      title: '发布时间',
      dataIndex: 'publish_time',
      key: 'publish_time',
      width: 200,
      align: 'center'
    },{
      title: '操作',
      key: 'action',
      width: 200,
      align: 'center',
      render: (data) => (
        <span>
            {data.publish===0?
            <AuthButton auth_list={this.props.auth_list} auth_path='/website/notice/publish' auth_type='tag' checked={true} onClick={() => {this.setState({id:data.id,publish_modal:true,operation:'PUBLISH'}) }} >发布</AuthButton>:null}
            <AuthButton auth_list={this.props.auth_list} auth_path='/website/notice/edit'  auth_type='tag'checked={true} onClick={() => {this.props.history.push(`/website/edit-notice/${data.id}`) }} >编辑</AuthButton>
            <AuthButton auth_list={this.props.auth_list} auth_path='/website/notice/del'  auth_type='tag' checked={true} onClick={() => {this.setState({id:data.id,publish_modal:true,operation:'DELETE'})}} >删除</AuthButton>
        </span>
      )
    }
    ];

    return(
      <div>
         <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>网站管理</Breadcrumb.Item>
              <Breadcrumb.Item><Link to="/website/list-notice">文章列表</Link></Breadcrumb.Item>
          </Breadcrumb>

         <Card>
            <DefaultTable 
              title={() => { return this.get_title() }}
              columns={columns}
              data={this.state.data}
              onChange={(pageInfo) => { this.change(pageInfo) }} 
            />
          </Card>
           <Modal title='确认' visible={this.state.publish_modal}
            okText={'确定'} cancelText={'取消'}
            onOk={()=>{this.handle_ok()}} onCancel={()=>{this.setState({publish_modal: false})}} >
              确认{this.state.operation==='PUBLISH'?'发布':'删除'}？
           </Modal>
        </div>

    );
  }

}