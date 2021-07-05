import React from 'react';
import {post} from 'common/util/request';
import { Card,Breadcrumb,Table,Modal,Input,message } from 'antd';
import { Link } from 'react-router-dom'
import AuthButton from 'common/components/auth/AuthButton';
const {TextArea}=Input;

export default class Deploy extends React.Component{
    
    state = {
        name:null,
        name_value:null,
        description:null,
        data:{
            page_num:1,
            page_size:10,
            list:[]
        },
        visible:false,
        delete_visible:false
    }
    async componentDidMount() {
        this.load_data();
    }

    async load_data(){
        const { data,name } =this.state;
       const result=await post("/deploy/list-deploy",{page_num:data.page_num,page_size:data.page_size,name});
       if(result.code ==='SUCCESS'){
           this.setState({data:result.data,name:null});
       }
    }

    async update(){
        const {name,name_value,description} =this.state;
        const result=await post("/deploy/update-deploy",{name,name_value,description});
        this.load_data();
        if(result.code==='SUCCESS'){
            message.success("修改成功！")
            this.setState({visible:false});
            return;
        }

        message.error("修改失败！")
        this.setState({visible:false});
    }


    async delete(){
        const { name } =this.state;
        const result=await post("/deploy/delete-deploy",{name});
        this.load_data();
        if(result.code==='SUCCESS'){
            message.success("删除成功！")
            this.setState({delete_visible:false});
            
            return;
        }

        message.error("修改失败！")
        this.setState({delete_visible:false});
    }
  
    render(){
        const columns = [
            {
              title: '节点',
              key: 'name',
              dataIndex: 'name',
            },{
              title: '默认值',
              dataIndex: 'default_value',
            },{
                title: '当前值',
                dataIndex: 'name_value',
              },{
                title: '描述',
                dataIndex: 'description',
            },{
                title: '操作',
                key: 'action',
                width: 200,
                align: 'center',
                render: (data) => (
                  <span>
                      <AuthButton auth_list={this.props.auth_list} auth_path='/develop/deploy/update' auth_type='tag' checked={true} onClick={() => {this.setState({visible:true,name:data.name,name_value:data.name_value,description:data.description}) }} >修改</AuthButton>
                      <AuthButton auth_list={this.props.auth_list} auth_path='/develop/deploy/delete'  auth_type='tag'checked={true} onClick={() => {this.setState({delete_visible:true,name:data.name})}} >删除</AuthButton>
                  </span>
                )
              }
          ];
        return(
            <div>
                <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item>开发管理</Breadcrumb.Item>
                    <Breadcrumb.Item><Link to="/website/list-deploy">配置中心</Link></Breadcrumb.Item>
                </Breadcrumb>

                <Card>
                    <Table rowKey={'name'}columns={columns} dataSource={this.state.data.list} pagination={{ pageSize: 10 }}/>
                </Card>

                <Modal title="修改" visible={this.state.visible} onOk={()=>{this.update()}} onCancel={()=>{this.setState({visible:false})}} >
                    <Input placeholder="名称" disabled onChange={(e)=>{this.setState({name:e.target.value})}} value={this.state.name} />
                    <TextArea style={{marginTop:"20px"}} placeholder="当前值"  onChange={(e)=>{this.setState({name_value:e.target.value})}} value={this.state.name_value}  />
                    <TextArea style={{marginTop:"20px"}} rows={6}  placeholder="描述"onChange={(e)=>{this.setState({description:e.target.value})}} value={this.state.description}  />
                </Modal>


                <Modal title="删除" visible={this.state.delete_visible} onOk={()=>{this.delete()}} onCancel={()=>{this.setState({delete_visible:false})}} >
                    <p>确认删除？请谨慎操作！！！</p>
                </Modal>


            </div>
        )
    }
}