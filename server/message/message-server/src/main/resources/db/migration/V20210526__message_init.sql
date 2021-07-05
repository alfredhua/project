
CREATE TABLE m_message.wx_user_info (
   `id` varchar(32) NOT NULL COMMENT 'id号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `nickname` varchar(100) DEFAULT NULL,
  `openid` varchar(100) NOT NULL DEFAULT '' COMMENT '微信的openId',
  `headimgurl` varchar(250) DEFAULT NULL COMMENT '头像',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `country` varchar(100) DEFAULT NULL COMMENT '国家',
  `unionid` varchar(100) DEFAULT NULL COMMENT 'unionid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '微信信息';

CREATE TABLE m_message.sms_records (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `numbers` text COMMENT '手机号',
  `content` text COMMENT '内容',
  `channel_type` varchar(20) DEFAULT NULL comment '渠道类型',
  `result` varchar(200) DEFAULT NULL COMMENT '发送结果',
  `receive_time` datetime DEFAULT NULL COMMENT '接受时间',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态:1初始，2:发送成功,3发送失败',
  `template_code` varchar(20) DEFAULT NULL COMMENT '模板编码',
  `error_msg` varchar(150) DEFAULT NULL COMMENT '错误码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信消息表';



