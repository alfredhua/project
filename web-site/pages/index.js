import React from 'react';
import Head from 'next/head'
import { MailOutlined, AppstoreOutlined, SettingOutlined } from '@ant-design/icons';
import { Layout, Menu, Breadcrumb } from 'antd';
const { Header, Content, Footer } = Layout;
const { SubMenu } = Menu;

class Index extends React.Component{

    state = {
      current: 'mail',
    };

    handleClick = e => {
      console.log('click ', e);
      this.setState({ current: e.key });
    };

  render(){
    return (
      <div >
        <Head> 
           <title>首页-码农阿华的个人博客</title>
           <meta name="keywords" content={"华,码农阿华,码农阿华博客,码农阿华的博客,个人博客,alfredhua,alfredhua的博客,网站,网站建设,权限管理,技术,技术管理,java,面试,"+
  "redis,docker,dubbo,swagger,swagger2,grpc,zk,zookeeper,产品,自动化测试,工具,设计模式,并发"}/>
            <meta name="description" content={"首页-码农阿华的个人博客,alfredhua,alfredhua.com,个人博客,博主,本项目是自研发的一套博客网站类系统,相应的开发出一套完整的代码生成工具。"+
          "后台采用Spring Boot,Dubbo,zookeeper框架,api生成工具swagger2,后台以及完成多个功能模块,"+
          "如：登录,角色认证,博文管理,网站管理,数据字典管理,等多个模块的研发工作,前端采用了koa,"+
          "react以及nodejs做中间层,进行网站搭建,使用docker作为微服务部署工具,数据库版本采用mybatis-migration进行管理。"+
          "独自研发,后台管理,权限管理,博客管理,文章管理,网站管理,网站建设,"+
          "精通多种技术：redis,docker,dubbo,swagger,swagger2,grpc,zk,zookeeper,产品,自动化测试,工具,设计模式,并发等"} />
        </Head>
        <Layout className="layout">
            <Header>
              <div className="logo" />
              <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
                {new Array(3).fill(null).map((_, index) => {
                  const key = index + 1;
                  return <Menu.Item key={key}>{`nav ${key}`}</Menu.Item>;
                })}
              </Menu>
            </Header>
            <Content style={{ padding: '0 50px' }}>
              <div className="site-layout-content">Content</div>
            </Content>
            {/* <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer> */}
          </Layout>
      </div>
    )
  }
}

Index.getInitialProps = async (res) => {
  // return Promise.all().then((result) => {
  //   return {}
  // });
  return {};
}

export default Index;