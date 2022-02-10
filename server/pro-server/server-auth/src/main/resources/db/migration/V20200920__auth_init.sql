CREATE TABLE m_auth.`auth_admin` (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `user_name` varchar(150) NOT NULL COMMENT '管理员姓名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `role_id_list` text NOT NULL COMMENT '用户角色id数据',
  `phone` varchar(11) NOT NULL COMMENT '管理员电话号码',
  `email` varchar(150) NOT NULL COMMENT 'email',
  `password` varchar(150) NOT NULL COMMENT '用户密码',
  `update_password` tinyint(2) DEFAULT '0' COMMENT '是否修改过密码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否激活,1冻结,0:激活',
  `auth_data_code` varchar(128) DEFAULT NULL COMMENT '权限码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理用户表';


CREATE TABLE  m_auth.`auth_data` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户数据权限表';


CREATE TABLE  m_auth.`auth_data_admin` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `admin_id` bigint(20) DEFAULT '0' COMMENT 'admin用户ID',
  `data_code` varchar(128) DEFAULT '0' COMMENT '数据权限编码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据权限关系表';


CREATE TABLE  m_auth.`auth_login_log` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `admin_id` bigint(20) NOT NULL COMMENT 'admin的id号',
  `ip_address` varchar(16) DEFAULT NULL COMMENT 'ip地址',
  `address` varchar(100) DEFAULT '' COMMENT '地点',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志表';


CREATE TABLE  m_auth.`auth_role` (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `name` varchar(150) NOT NULL COMMENT '角色名',
  `auth_list` text NOT NULL COMMENT '权限数组',
  `comment` varchar(150) DEFAULT NULL COMMENT '备注',
  `auth_data_code` varchar(128) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用,0激活,1禁用,',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


CREATE TABLE  m_auth.`u_login_log` (
  `id` varchar(32) NOT NULL COMMENT 'id号',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id号',
  `client_ip` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `address` varchar(200) DEFAULT NULL COMMENT '登录地址',
  `type` tinyint(2) DEFAULT '1' COMMENT '登录方式:1:账户密码,2:微信',
  `source` tinyint(2) DEFAULT NULL COMMENT '登录源,1:PC,2:H5,3:MOBILE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE  m_auth.`u_user` (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `id_card_number` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `source` varchar(10) NOT NULL DEFAULT 'PC' COMMENT '注册来源',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态,1:正常，2冻结',
  `login_fail_count` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';


CREATE TABLE  m_auth.`u_wx_info` (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `openid` varchar(100) DEFAULT NULL COMMENT '微信的openId',
  `headimgurl` text COMMENT '头像',
  `unionid` varchar(100) DEFAULT NULL COMMENT 'unionid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户微信信息';



INSERT INTO  m_auth.`auth_admin` (`id`, `user_name`, `create_time`, `update_time`, `role_id_list`, `phone`, `email`, `password`, `update_password`, `status`, `auth_data_code`) VALUES
	(1,'admin','2018-11-06 17:06:07','2021-05-24 23:01:55','[1]','18331385723','aaa@163.com','ZwsUcorZkCrsujLiL6T2vQ==',0,1,'SYSTEM'),
	(1416024213684256,'demo','2021-05-25 07:09:22','2021-05-25 22:08:50','[1416023976706080]','18331385758','demo@qq.com','ZwsUcorZkCrsujLiL6T2vQ==',0,1,'DEMO');


INSERT INTO  m_auth.`auth_data` (`id`, `name`, `code`, `create_time`, `update_time`, `del`) VALUES
	(1,'管理员','SYSTEM','2021-05-23 18:22:14','2021-05-24 22:35:02',0),
	(1415964209971232,'DEMO','DEMO','2021-05-24 23:12:30','2021-05-24 23:12:30',0);



INSERT INTO  m_auth.`auth_data_admin` (`id`, `admin_id`, `data_code`, `create_time`, `update_time`, `del`) VALUES
	(1,1,'SYSTEM','2021-05-24 22:34:07','2021-05-24 22:34:07',0),
	(1416024255627296,1416024213684256,'DEMO','2021-05-25 07:09:42','2021-05-25 07:09:42',0);

INSERT INTO  m_auth.`auth_role` (`id`, `name`, `auth_list`, `comment`, `auth_data_code`, `create_time`, `update_time`, `status`) VALUES
	(1,'超级管理员','["/auth","/auth/role","/auth/role/list","/auth/role/create","/auth/role/edit","/auth/admin","/auth/admin/list","/auth/admin/create","/auth/admin/edit","/auth/admin/reset","/dictionary/article-type","/dictionary/article-type/list","/dictionary/article-type/create","/dictionary/article-type/edit","/dictionary/article-type/active","/dictionary/article-type/close","/dictionary/article-type/watch-article","/dictionary/article-type/del","/website/article","/website/article/list","/website/article/create","/website/article/edit","/website/article/publish","/auth/data","/auth/data/list","/auth/data/create","/dictionary","/dictionary/notice-type","/dictionary/notice-type/list","/dictionary/notice-type/create","/dictionary/notice-type/edit","/dictionary/notice-type/active","/dictionary/notice-type/close","/dictionary/notice-type/watch-article","/dictionary/notice-type/del","/website","/website/notice","/website/notice/list","/website/notice/create","/website/notice/edit","/website/notice/publish","/website/banner","/website/banner/list","/website/banner/create","/website/banner/edit","/website/banner/del","/website/news","/website/news/list","/website/news/create","/website/news/edit","/website/news/publish","/website/news/recall","/website/news/del","/website/produce","/website/produce/list","/website/produce/create","/website/produce/edit","/website/produce/del","/website/produce/set-detail","/website/setting","/website/setting/open","/website/setting/close","/website/partner","/website/partner/list","/website/partner/create","/website/partner/edit","/website/partner/del","/website/notice-type","/website/notice-type/list","/website/notice-type/create","/website/notice-type/edit","/website/notice-type/active","/website/notice-type/close","/website/notice-type/watch-article","/website/notice-type/del","/blog","/blog/article","/blog/article/list","/blog/article/create","/blog/article/edit","/blog/article/publish","/blog/article/recall","/blog/article/watch-comment","/blog/article/delete","/blog/type","/blog/type/list","/blog/type/create","/blog/type/edit","/blog/type/del","/blog/type/enable","/develop","/develop/sql","/develop/sql/list","/develop/swagger","/develop/swagger/list","/develop/deploy","/develop/deploy/list","/develop/deploy/update","/develop/deploy/delete"]','超级管理员','SYSTEM','2018-11-03 20:35:27','2021-05-25 21:51:36',0),
	(1416023976706080,'DEMO','["/auth/admin","/auth/admin/list","/auth/admin/create","/auth/admin/edit","/auth/admin/reset","/auth/admin/set-auth-data","/auth","/website/banner","/website/banner/list","/website/banner/create","/website/banner/edit","/website/banner/del","/website","/auth/role","/auth/role/list","/auth/role/create","/auth/role/edit","/develop","/develop/sql","/develop/sql/list","/develop/swagger","/develop/swagger/list","/develop/deploy","/develop/deploy/list","/develop/deploy/update","/develop/deploy/delete"]','DEMO','DEMO','2021-05-25 07:07:29','2021-05-26 22:36:02',0);



