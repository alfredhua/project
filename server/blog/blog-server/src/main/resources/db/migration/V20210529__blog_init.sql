
create table m_blog.blog_article(
   `id` bigint(20) NOT NULL primary key COMMENT 'id号',
   `title` varchar(150) NOT NULL COMMENT '标题',
    `type` varchar(20) NOT NULL COMMENT '文章分类',
    `pic_url` varchar(200) NOT NULL COMMENT '封面url',
    `click_count` int(11) DEFAULT '0' COMMENT '点击数',
    `like_count` int(11) DEFAULT '0' COMMENT '赞赏数',
    `context` text NOT NULL COMMENT '内容',
     `reprint` tinyint(2) DEFAULT '0' COMMENT '0:原创,1:转载，',
    `status` tinyint(2) DEFAULT '0' COMMENT '1:待发布，2:已发布，',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '博客文章表';


create table m_blog.blog_article_comment(
   `id` bigint(20) NOT NULL primary key COMMENT 'id号',
   `blog_article_id` bigint(20) NOT NULL COMMENT '文章id号',
   `user_name` varchar(150) default 0 COMMENT '用户名',
   `context` text not null comment '评论内容',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '博客文章表';


create table m_blog.blog_type(
  `id` bigint(20) NOT NULL primary key COMMENT 'id号',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `status` tinyint(2) DEFAULT '0' COMMENT '是否激活:0未激活,1:激活',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '博客类型表';



alter table m_blog.blog_type add introduce varchar(200) default null comment '简介';

insert INTO m_blog.blog_type (`id`, `type`, `name`, `status`, `create_time`, `update_time`, `del`, `introduce`) VALUES
	(1301366347661343,'JAVA','java',1,'2019-08-31 02:48:21',NULL,0,'玩玩技术'),
	(1301366347661344,'REDIS','redis',1,'2019-08-31 12:10:32',NULL,0,NULL),
	(1301366429450272,'REBBITMQ','rabbitmq',1,'2019-08-31 12:11:11',NULL,0,NULL),
	(1301366429450273,'MYBATIS','mybatis',1,'2019-09-01 09:02:06',NULL,0,NULL),
	(1301366429450274,'SPRING','spring',1,'2019-09-01 09:02:44',NULL,0,NULL),
	(1301366429450275,'SPRING_BOOT','spring boot',1,'2019-09-01 09:03:06',NULL,0,NULL),
	(1301366429450276,'GRPC','grpc',1,'2019-09-01 09:03:39',NULL,0,NULL),
	(1301366429450278,'DUBBO','dubbo',1,'2019-09-01 09:03:57',NULL,0,NULL),
	(1301366429450279,'ZOOKEEPER','zookpeer',1,'2019-09-01 09:04:11',NULL,0,NULL),
	(1301366429450280,'JVM','jvm',1,'2019-09-01 09:05:50',NULL,0,NULL),
	(1301366429450281,'CESHI','自动化测试',1,'2019-09-01 09:06:05',NULL,0,NULL),
	(1301366429450282,'PRODUCE','产品',1,'2019-09-01 09:06:36',NULL,0,'说说产品'),
	(1301366429450283,'MS','面试',1,'2019-09-01 09:06:54',NULL,0,NULL),
	(1301366429450284,'TOOL','工具',1,'2019-09-01 09:07:38',NULL,0,NULL),
	(1301366429450285,'DESGIN','设计模式',1,'2019-08-01 09:08:16',NULL,0,NULL),
	(1301366429450286,'BF','并发',1,'2019-09-01 09:11:17',NULL,0,NULL);


alter table m_blog.blog_article add introduce varchar(200) not null comment '简介';

alter table m_blog.blog_article add content_type tinyint(2) default 0 not null comment '编辑类型';


