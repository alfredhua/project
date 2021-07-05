import React from 'react';
import { message,Modal,Input,Card,Menu,Dropdown,Button,Icon,Select } from 'antd';
import DefaultTable from 'common/components/table/DefaultTable';
import AuthButton from 'common/components/auth/AuthButton';
import {post} from 'common/util/request';
const Option=Select.Option;

export default class NoticeTypeList extends React.Component{

    state = {
        data: {
          page_num: 0,
          page_size: 10,
          list:[]
        },
        id:null,
        name:null,
        type:null,
        visible: false,
        act_visible:false,
        enable:false,
        status:null,
        auth_data_code:null,
        auth_data:[]
    }
    componentDidMount() {
        this.load_data(this.state.data.page_num, this.state.data.page_size);
        this.load_auth_data();
    }

    async load_auth_data(){
      const {code,data} = await post("/auth/list-all-auth-data",{});
      if(code === 'SUCCESS' ){
        this.setState({auth_data:data});
      }
    }

    async load_data(page_num, page_size){
      const {code,data,msg}= await post('/website/list-notice-type-by-page',{page_num, page_size});
      if ('SUCCESS' === code) {
          this.setState({data,visible: false,act_visible:false});
      }else{
        message.error('获取失败:'+msg);
      }
    }

    async updateActive(){
      const { id,status } =this.state;
      const result= await post('/website/update-notice-type-status',{id,status});
      if ('SUCCESS' === result.code) {
        message.success('保存成功!');
        this.load_data(this.state.data.page_num, this.state.data.page_size);
      }else{
        message.error('删除失败:'+result.msg);
      }
    }
    async save(){
        const {type,name,id,auth_data_code}=this.state;
        let result;
        if(id===null){
          result= await post('/website/create-notice-type',{name,type,auth_data_code});
        }else{
          result= await post('/website/update-notice-type',{id,name,type,auth_data_code});
        }
        if ('SUCCESS' === result.code) {
            message.success('保存成功!');
            this.load_data(this.state.data.page_num, this.state.data.page_size);
        }else{
          message.error('保存失败:'+result.msg);
        }
        this.setState({visible: false,enable:false})
    }


    page_to(pageInfo) {
      const { current, pageSize } = pageInfo;
      this.load_data(current, pageSize);

    }

    getTitle() {
        return (
          <div>
            <span> 
                <AuthButton auth_list={this.props.auth_list} auth_type="button" auth_path='/website/notice-type/create'   size='small' type='primary' onClick={()=>{this.setState({id:null,name:null,type:null,visible:true})}}> 添加</AuthButton>
            </span>
          </div>
        );
      }
      get_menu(data){
        return(
          <Menu>
              <AuthButton onClick={() => { this.setState({id:data.id,name:data.name,type:data.type,visible:true,enable:true,auth_data_code:data.auth_data_code}) }} auth_list={this.props.auth_list}  auth_path='/website/notice-type/edit' auth_type='menu-item'>修改</AuthButton>
              {data.status===0?
              <AuthButton auth_list={this.props.auth_list}  auth_path='/website/notice-type/active' onClick={() => { this.setState({id:data.id,status:1,act_visible:true})}} auth_type='menu-item'>激活</AuthButton>:
              <AuthButton auth_list={this.props.auth_list}  auth_path='/website/notice-type/close' onClick={() => { this.setState({id:data.id,status:0,act_visible:true})}} auth_type='menu-item'>关闭</AuthButton>}
              <AuthButton auth_list={this.props.auth_list}  auth_path='/website/notice-type/watch-notice' onClick={() => { this.props.history.push(`/website/list-notice/${data.type}`) }} auth_type='menu-item'>查看文章内容</AuthButton>
          </Menu>
        );
      }
    render(){
        const columns = [{
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
            align: 'center'
          }, {
            title: '创建时间',
            dataIndex: 'create_time',
            key: 'create_time',
            width: 150,
            align: 'center'
          }, {
            title: '状态',
            dataIndex: 'status',
            key: 'status',
            width: 150,
            align: 'center',
            render:(status)=>(
              <span>{status===0?"关闭":"激活"}</span>
            )
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
          const {auth_data} =this.state;
        return(
            <div>
                <Card>
                    <DefaultTable 
                        title={() => { return this.getTitle() }}
                        data={this.state.data}
                        columns={columns}
                        onChange={(pageInfo) => { this.page_to(pageInfo) }} 
                        /> 
                </Card>

                     <Modal
                        title="添加"
                        visible={this.state.visible}
                        onOk={()=>{this.save()}}
                        onCancel={()=>{this.setState({visible: false,enable:false})}}
                        okText="确认"
                        cancelText="取消">
                        <Input placeholder="名称" onChange={(e)=>{this.setState({name:e.target.value})}} value={this.state.name} />
                        <Input style={{marginTop:"20px"}} placeholder="类型" disabled={this.state.enable} onChange={(e)=>{this.setState({type:e.target.value})}} value={this.state.type}  />
                        <div style={{color:'red'}}>类型请用英文大写，例如：HELP</div>

                        <Select style={{width:200}} onChange={(value)=>{this.setState({auth_data_code:value})}} value={this.state.auth_data_code}>
                        {auth_data&&auth_data.map(item=>{
                          return <Option  key={item.code} value={item.code}>{item.name}</Option>
                        })}
                    </Select>
                     </Modal>

                     <Modal
                        title="确认信息"
                        visible={this.state.act_visible}
                        onOk={()=>{this.updateActive()}}
                        onCancel={()=>{ this.setState({act_visible: false})}}
                        okText="确认"
                        cancelText="取消"
                        >
                        确认{this.state.status===1?"激活":"关闭"}？
                     </Modal>
            </div>
        )
    }
}