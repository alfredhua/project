## 基于Spring boot Dubbo 的博客，网站系统

本项目是一个基于Spring boot, Dubbo, Mybatis，MySQL，mybatis-migration的博客类，网站类系统

## 网站预览

- 后台管理 [admin.alfredhua.com](http://admin.alfredhua.com)
- 博客网站 [site.alfredhua.com/blog/home](http://site.alfredhua.com/blog/home)
- 网站  [site.alfredhua.com/website/home](http://site.alfredhua.com/website/home)

## 技术栈

- Jdk8：java8比较成熟。
- gradle：更便捷。
- groovy：单元测试代码使用groovy，复杂业务测试更方便
- Spring Boot :微服务入门级框架，自动化配置，约定大于配置的方式，简化开发过程。
- Spring-boot-actuator: 服务监控，管理。
- dubbo：rpc框架，服务直接调用，目前国内流行的rpc之一。
- MySQL5.7： 开源的MySQL，大众之选。
- RabbitMQ：MQ的选择使用RabbitMQ，相对比较成熟，erlang语言开发，性能极其好，延时很低；吞吐量到万级，MQ功能比较完备；而且开源提供的管理界面非常棒，用起来很好用；社区相对比较活跃；即使发现问题，在社区里也几乎能找到相应的解决办法。
- redis：缓存管理
- zookeeper：注册中心，dubbo的组合，zk也可以做分布式锁。
- react：react相对vue来说，入门稍微难点，但是学会不难，vue的话，入门简单，深入更难。
- nodejs：前端的首选，中间层可以做优化。
- koa：基于Node.js 的下一代web开发框架。
- Mybatis-migration：数据库的版本管理。
- docker，docker-compose：部署神器。
- Swagger2：api接口文档生成工具。
- lombok：简化代码。
- generator：适应于本项目而研发的一套代码生成器工具，github地址：https://github.com/alfredhua/generator


## 项目模块说明

| 模块         | 服务       | 端口            |
| ----------- | --------- | --------------- |
| auth-server    |   权限   dubbo服务     | 20880端口   |
| blog-server    |   博客  dubbo服务     | 220881端口   |
| dictionary-server    |   字典  dubbo服务     | 20882端口   |
| mq-server   |  RabbitMQ  dubbo服务     | 20883端口   |
| sms-server    |   短信  dubbo服务     | 20884端口   |
| user-server    |   用户  dubbo服务     | 20885端口   |
| website-server    |  网站服务  dubbo服务     | 20886端口   |
| wechar-server    |   微信  dubbo服务     | 20887端口   |
| web-admin    |  web服务，后台接口     | 4001端口   |
| web/web-site    |  web服务，前端接口      | 5001端口   |
| web/web-quartz    | 定时任务     | 7001端口   |
| common    |  公有模块，基础工具类     | 无   |



## 项目预览

### 后台admin系统

访问：http://127.0.0.1:4000 

默认账号：admin，密码：000000

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/c05aa02ec40c4901b9075649a767e2f9.png)

权限管理
![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/blog/pvoOFb.png)

网站管理

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/c1f5b28034ba4b0784e73ed610c9e086.png)

博文管理

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/d2165b84791b4e6ebacf604ca837c55c.png)

权限管理

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/6dec5306978d41cf807bb8dc215a009f.png)


## 前端页面预览

访问：http://127.0.0.1:5000 

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/8057d6c6440f4feba508fb54bcaa6006.png)

## 网站预览

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/23cb0fb2f64d4df9893c72df4fec081e.png)


## 手机站预览
访问：http://127.0.0.1:5100
 
![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/a08691feb19e4388abe0050e060000f2.jpeg)

![image](https://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/73cae59a55c84b3094b1638d1ab31099.jpeg)

## 在线预览地址

- 前端：https://alfredhua.com/blog/home/
- 手机站：https://m.alfredhua.com/blog/home/
- 后台：http://admin.alfredhua.com/
- 网站预览地址：https://alfredhua.com/website/home

## 服务介绍

- auth：权限认证

- blog：博客管理

- common：公有模块，可以直接打包引入，参考https://github.com/alfredhua/common-spring-boot-start

  可以进行打包，供其他模块引入

- dictionary：数据字典管理

- mq：mq服务模块

- sms：短信模块

- user：前端用户管理模块

- web：服务集成中心

  - web-admin： 后台管理Api，端口：4001
  - web-service：后台服务集中启动(目前集中启动，方便个人开发，如有需要，可以每个单服务启动）。端口：3001
  - web-mq: mq服务，端口：6001
  - web-site：前端服务api，端口：5001
  - web-quartz：定时任务模块

- website： 网站基础服务模块
- wechat：微信接口服务模块
  

#### 安装教程

1. 使用idea打开 server目录下的build.gradle

2. 安装lombok。

3. 数据库安装 mysql, mybatis-migration,执行migrate/database.sql创库脚本，

   密码账户默认都是manage，在项目目录下执行./tool/dev-migrate.sh 
4. 在web-admin下执行yarn install
5. 在web-site 下执行yarn install
   
#### 生产环境部署教程
1. 启动项目，执行docker-compose up -d 即可
2. 启动前端服务 ./tool/proc/start-site.sh 即可
3. 配置nginx，页面指定编译目录即可，请求接口全部为/api，代理到相应的node服务即可

# 开发规则

## 后端开发规则

1. 统一使用idea开发，不要用其它的idea，一个团队的idea应该保持统一。
2. idea的配置文件和临时文件不要提交到git上，因为会影响别人的开发。
3. 所有方法规则：创建：create，获取请求使用get，更新请求使用:update,列表使用list，删除使用del开头，其余业务按照自己处理业务规则进行相应的命名开头。
4. 数据库采用下划线，与java对应，JAVA所有的实体的命名方式都用下划线方式连接(不要用驼峰式)，与数据库命名规则保持一致，其余（类名，方法名等）一律按照java驼峰式风格处理。
5. 所有的url都是用“-”来连接，方便清晰查看
6. 复杂的业务逻辑在dao下新建java，简单的业务逻辑可以直接在service层写。(这种方式,可以保持代码的清洁)
7. 不要直接在master提交任何东西，master是合并其它的分支的，永远与生产保持一致(免测的bug，可以直接上master)。
8. 所有的状态都用枚举定义,否则其他人没人知道该状态是什么意思。

### 前端人员开发

1. 前端开发，自定义的方法，都统一采用下划线，antd的组件安装antd规则方式。
2. 所有的sql不许出现“select * from”这种语句，一律都写上列名，最好写一个公有。
3. 前端编辑器可以随意。不做强求，但是格式化的缩进必须一致。

#### 使用说明

1. service服务提供方：./tools/start.sh service

2. web-admin接口：./tool/start.sh admin

3. web-site接口 : ./tools/start.sh site

4. Web-quartz接口: ./tools/start.sh quartz

5. Web-quartz: ./tools/start.sh quartz

6. admin，运行：yarn dev:admin 启动服务

7. site:  
       pc服务启动: yarn dev:pc
       h5服务启动: yarn dev:h5

   ​    全部服务启动：yarn dev:all

## 参与贡献

1. guozhenhua：邮箱：hua_zhenguo@aliyun.com

