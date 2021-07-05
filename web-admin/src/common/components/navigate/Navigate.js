import React from 'react';
import { Link } from 'react-router-dom'
import menu_list from 'common/components/navigate/menu';
import {isok,menu_tree_auth} from 'common/util/Auth';
import {post} from 'common/util/request';
import './style/style.css';
import { Layout, Menu, Icon,Avatar,Dropdown,message } from 'antd';
const { Content, Footer, Sider,Header} = Layout;
const SubMenu = Menu.SubMenu;

export default class Navigate extends React.Component {
  
  state = {
    collapsed: false,
  };

  onCollapse = (collapsed) => {
    this.setState({ collapsed });
  }

  get_sub_menu(item,partner_index){
    if(menu_tree_auth(this.props.admin_info.auth_list,item.auth_path)){
        return(
          <SubMenu  key={partner_index}  title={<span><Icon type={item.icon} /><span>{item.label}</span></span>}>
              {item.sub_menu.map((sub_item,index)=>{
                if(isok(this.props.admin_info.auth_list,sub_item.auth_path)){
                    return(
                      <Menu.Item key={partner_index+"-"+index}>
                        <Link to={sub_item.link}><Icon type={sub_item.icon} />{sub_item.label}</Link>
                      </Menu.Item>
                    )
                }else{
                  return null;
                }
              })}
          </SubMenu>
        )
     }else{

     }
  }

  async logout(){
      let result=await post("/logout",{});
      if(result && result.code === 'SUCCESS' ){
        this.props._props.history.push('/login');
      }else{
        message.success("保存失败!"+result.msg);
      }
  }
  click(item){
    if(item.key==='logout'){
       this.logout();
    }
  }

  render() {
    const menu = (
       <Menu style={{width:150}} onClick={(item,key)=>{this.click(item)}}>
         <Menu.Item key="user" style={{width:150}}>
            <div>
               <Icon type="user" style={{float:'left',padding:'5px 5px'}}/><Link style={{padding:'5px 5px'}} to={`/auth/update-info`}>{'用户信息'}</Link>
           </div>
         </Menu.Item>
         <Menu.Item key="setting" style={{width:150}}>
           <div>
             <Icon type="setting" style={{float:'left',padding:'5px 5px'}}/><Link style={{padding:'5px 5px'}} to={'/auth/update-password'}>{'修改密码'}</Link>
          </div>
          </Menu.Item>
         <Menu.Divider />
         <Menu.Item key="logout">
            <div >
             <Icon type="logout" style={{float:'left',padding:'5px 5px'}}/><span style={{padding:'5px 5px',color:'#1da57a'}}>{'退出登录'}</span>
           </div>
         </Menu.Item>
       </Menu>
    );

    return (
      <Layout style={{minHeight:'100vh'}}>
          <Sider collapsible  collapsed={this.state.collapsed} onCollapse={this.onCollapse}  >
            <div className="logo" >
              <span>管理</span>
            </div>
              <Menu theme="dark" mode="inline">
                {menu_list.map((item,index)=>{
                  if(item.sub_menu){
                     return  this.get_sub_menu(item,index);
                   }else{
                      if(menu_tree_auth(this.props.admin_info.auth_list,item.auth_path)){
                        return (
                          <Menu.Item key={index}>
                            <Link to={item.link}><Icon type={item.icon} />  <span>{item.label}</span></Link>
                          </Menu.Item>
                        )
                      }else{
                        return null;
                      }
                  }
                })}
              </Menu>
          </Sider>

          <Layout>
                  
              <Header style={{ background: '#fff', padding: 0 }} >
                {/* <Select  defaultValue="lucy" style={{ marginLeft:20}} onChange={()=>{}}>
                    <Option value="jack"><Icon type="book" /> 业务中心</Option>
                    <Option value="lucy"><Icon type="setting" theme="filled" /> 系统设置</Option>
                    <Option value="Yiminghe"><Icon type="snippets" />开发设置</Option>
                  </Select> */}
                  <Dropdown  style={{width:100 }} overlay={menu} placement="bottomLeft">
                    <span style={{width:100 ,float:'right'}}>
                      <Avatar size="small" style={{ backgroundColor: '#87d068' }} icon="user" alt="avatar" /> <span >{this.props.admin_info.user_name}</span>
                    </span>
                  </Dropdown>
              </Header>

              <Content style={{ margin: '10px 20px' }}>
                <div style={{  minHeight: 360 }}>
                     {this.props.children}
                </div>   
              </Content>
              <Footer style={{ textAlign: 'center' }}>
                     design by 华乐
              </Footer>
          </Layout>
      </Layout>
    );
  }
}