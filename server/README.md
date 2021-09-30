## 后台服务模块

## 目录架构
    每个服务下分为2个子目录，
    1. XX-api：dubbo接口api
    2. XX-server：服务的提供方
## 服务介绍

- auth：权限认证
- blog：博客管理
- common：公有模块，可以直接打包引入，参考https://github.com/alfredhua/common-spring-boot-start
  可以进行打包，供其他模块引入
- dictionary：数据字典管理
- mq：mq服务模块
- com.message.sms：短信模块
- com.auth.user：前端用户管理模块
- web：服务集成中心
  - Web-admin： 后台管理Api，端口：4001
  - web-site：前端服务api，端口：5001
- website： 网站基础服务模块
- wechat：微信接口服务模块


  