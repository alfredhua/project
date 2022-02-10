CREATE TABLE m_develop.de_deploy (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `name` varchar(30) DEFAULT '0' COMMENT '节点名称',
  `name_value` varchar(100) DEFAULT NULL COMMENT '节点值',
  `description` varchar(100) DEFAULT NULL COMMENT 'desc',
  `operator` varchar(30) NOT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置管理';