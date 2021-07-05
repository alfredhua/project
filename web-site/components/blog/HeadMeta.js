import React from "react";
import { Helmet } from "react-helmet";

export const default_meta={
  title:"首页-码农阿华的个人博客",
  keywords:"华,码农阿华,码农阿华博客,码农阿华的博客,个人博客,alfredhua,alfredhua的博客,网站,网站建设,权限管理,技术,技术管理,java,面试,"+
  "redis,docker,dubbo,swagger,swagger2,grpc,zk,zookeeper,产品,自动化测试,工具,设计模式,并发",
  description:"首页-码农阿华的个人博客,alfredhua,alfredhua.com,个人博客,博主,本项目是自研发的一套博客网站类系统,相应的开发出一套完整的代码生成工具。"+
  "后台采用Spring Boot,Dubbo,zookeeper框架,api生成工具swagger2,后台以及完成多个功能模块,"+
  "如：登录,角色认证,博文管理,网站管理,数据字典管理,等多个模块的研发工作,前端采用了koa,"+
  "react以及nodejs做中间层,进行网站搭建,使用docker作为微服务部署工具,数据库版本采用mybatis-migration进行管理。"+
  "独自研发,后台管理,权限管理,博客管理,文章管理,网站管理,网站建设,"+
  "精通多种技术：redis,docker,dubbo,swagger,swagger2,grpc,zk,zookeeper,产品,自动化测试,工具,设计模式,并发等",
};


export default class HeadMeta extends React.Component {
  render () {
   const { meta } =this.props;
    const title =meta?meta.title:default_meta.title
    const description=meta?meta.description:default_meta.description
    const keywords=meta?meta.keywords:default_meta.keywords
    return (
       <Helmet>
            <meta charSet="utf-8" />
            <title>{title}</title>   
            <meta name="keywords" content={keywords}/>
            <meta name="description" content={description} />
        </Helmet>
    );
  }
};