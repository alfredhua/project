import React from 'react';
import {post} from 'common/util/request';
import { Breadcrumb } from 'antd';
import { Link } from 'react-router-dom'

export default class Swagger extends React.Component{
    state = {
        url:'',
        iFrameHeight:'0px'
    }
    async componentDidMount() {
        this.load_data()
    }

    async load_data(){
        const {url}=await post("/deploy/get-swagger",{});
        this.setState({url});
    }
  
    render(){
        return(
            <div>
                  <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item>开发管理</Breadcrumb.Item>
                    <Breadcrumb.Item><Link to="/website/list-swagger">API文档</Link></Breadcrumb.Item>
                </Breadcrumb>

                <iframe 
                title="Swagger API文档" 
                style={{width:'100%', minHeight:800, overflow:'visible'}}
                src={this.state.url}></iframe>
            </div>
        )
    }
}