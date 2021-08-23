CREATE TABLE m_website.site_navigate (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `type` varchar(20) NOT NULL COMMENT '分类',
  `title` varchar(150) NOT NULL COMMENT '标题',
  `icon` varchar(200) NOT NULL COMMENT '封面url',
  `introduce` varchar(200) NOT NULL COMMENT '简介',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='导航网页表';

