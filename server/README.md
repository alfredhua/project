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


# common-spring-boot-start
    采用Spring-boot-start进行打包，作为一个共有的模块即可自动引入,执行./gradlew install 即可打成jar包并且加入到maven仓库。


# LimitTime
    在方法直接加注解@LimitTime即可

    limitValue

    type：默认：LimitTimeTypeEnum.NULL,其它：LimitTimeTypeEnum.LIMIT，LimitTimeTypeEnum.TIMEOUT

    timeValues：接口超时时间：LimitTimeTypeEnum.TIMEOUT 时生效。

    timeUnit：接口超时时间单位：LimitTimeTypeEnum.TIMEOUT 时生效。

    fallback：补偿方法，默认可以为空


# redis
    配置：
    spring.redis.host= 127.0.0.1
    spring.redis.port= 6379
    spring.redis.password= 
    如果需要配置redisson分布式锁增加配置文件即可。
    spring.redis.redisson.config = redisson.yaml

    使用：
        @Autowired
        RedisUtils redisUtils;  

        @Autowired
        RedisLockUtils redisLockUtils;  

## elasticsearch
    配置：
        es.cluster-name=elasticsearch
        es.ips=192.168.1.2:9300,192.168.1.3:9300
        es.cluster=false
    使用：
        @Autowired
        EsTransportClient esTransportClient;  


# mail
    对邮件发送进行了封装，修改配置文件:
        mail.port= 465
        mail.email_name= a@qq.com
        mail.email_password= password
        mail.mail_host= smtp.mxhichina.com
        mail.to_mail= mail@qq.com
    
    使用:
        MailUtil mailUtil;

# request response
    公有参数请求和公有的返回
# util
    GsonUtils：gson的转换
    HttpClient：对http的get和post进行封装。默认是json请求
    IDGenerate：分布式ID生成策略，配置机器的hostname
    MessageDigestUtil：镜像base64还有MD5加密
    PageUtil：进行分页
    BeanCopyUtil：进行实体之间对象属性copy
    SignUtil：签名

## BeanCopyUtil
    主要做实体之间的转换，主要对BeanUtils.copyProperties进行了二次封装
 