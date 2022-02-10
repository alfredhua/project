
DROP TABLE IF EXISTS m_website.site_setting_detail;
DROP TABLE IF EXISTS m_website.site_setting;
DROP TABLE IF EXISTS m_website.site_produce_detail;
DROP TABLE IF EXISTS m_website.site_produce;
DROP TABLE IF EXISTS m_website.site_partner;
DROP TABLE IF EXISTS m_website.site_news;
DROP TABLE IF EXISTS m_website.site_banner;
DROP TABLE IF EXISTS m_website.site_notice;


CREATE TABLE m_website.site_notice (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `type` varchar(30) NOT NULL DEFAULT '' COMMENT '类型',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `context` text NOT NULL COMMENT '文本',
  `publish` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否发布,0:未发布,1发布',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  `ordering` int(10) DEFAULT NULL COMMENT '排序',
  `click_count` int(10) NOT NULL DEFAULT '0' COMMENT '点击次数',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章管理';


CREATE TABLE m_website.site_banner (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT 'banner地址',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT 'banner名称',
  `href` varchar(100) DEFAULT NULL COMMENT '跳转地址',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否开启',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `order` int(10) DEFAULT NULL COMMENT '排序',
  `type` enum('PC','H5','APP','XXC') NOT NULL DEFAULT 'PC' COMMENT '类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='banner图';


CREATE TABLE m_website.site_news (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `publish` tinyint(1) DEFAULT '0' COMMENT '是否发布1:创建,2:发布,3:撤回',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `source_url` varchar(200) DEFAULT NULL COMMENT '来源地址',
  `pic` varchar(200) DEFAULT NULL COMMENT '列表预览图片',
  `introduce` varchar(200) DEFAULT NULL COMMENT '描述',
  `context` text COMMENT '内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻';


CREATE TABLE m_website.site_partner (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '合作伙伴名称',
  `href` varchar(100) DEFAULT NULL COMMENT '跳转地址',
  `pic_url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `pc_show` tinyint(2) DEFAULT '0' COMMENT 'pc是否展示，0不展示，1展示',
  `m_show` tinyint(2) DEFAULT '0' COMMENT '手机站是否展示，0不展示，1展示',
  `ordering` int(10) DEFAULT NULL COMMENT '排序',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合伙伙伴管理';


CREATE TABLE m_website.site_produce (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `title` varchar(50) DEFAULT NULL COMMENT '产品id号',
  `file_list` text COMMENT '产品图片',
  `cover_image` varchar(200) DEFAULT NULL COMMENT '封面图片',
  `pc_show` tinyint(2) NOT NULL DEFAULT '0' COMMENT 'pc是否展示，0不展示，1展示',
  `m_show` tinyint(2) NOT NULL DEFAULT '0' COMMENT '手机站是否展示，0不展示，1展示',
  `home_show` tinyint(2) NOT NULL DEFAULT '0' COMMENT '首页是否展示，0不展示，1展示',
  `ordering` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  `introduce` varchar(50) DEFAULT NULL COMMENT '简介',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';


CREATE TABLE m_website.site_produce_detail (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `produce_id` bigint(20) NOT NULL COMMENT 'id号',
  `context` text COMMENT '文章内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品文章介绍表';


CREATE TABLE m_website.site_setting (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `type` varchar(30) DEFAULT '0' COMMENT '类型',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `status` tinyint(2) DEFAULT '0' COMMENT '开启1，关闭0',
  `ordering` int(2) DEFAULT NULL COMMENT '排序',
  `partner_id` bigint(20) NOT NULL COMMENT '父节点id，默认是0',
  `desc` tinyint(2) DEFAULT '0' COMMENT '是否存在详情描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站设置管理';

INSERT INTO m_website.site_setting (`id`, `type`, `name`, `status`, `ordering`, `partner_id`, `desc`, `create_time`, `update_time`, `del`,`auth_data_code`) VALUES
	(1290312349646880,'NEWS','新闻动态',1,2,0,1,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646881,'PRODUCE','产品服务',1,1,0,1,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646882,'PARTNER','合作伙伴',1,4,0,1,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646883,'CONTACT_US','联系我们',0,6,1290312349646884,1,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646884,'ABOUT_US','关于我们',1,3,0,1,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646885,'COMPANY','企业文化',0,0,1290312349646884,0,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646886,'WANT','公司愿景',0,0,1290312349646884,0,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646887,'DEVELOP','发展历程',1,0,1290312349646884,0,'2019-07-08 14:41:00',NULL,0,'DEMO'),
	(1290312349646891,'NOTICE','网站公告',1,5,0,1,'2019-07-08 14:41:00',NULL,0,'DEMO');

CREATE TABLE m_website.site_setting_detail (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `type` varchar(30) DEFAULT '0' COMMENT '类型',
  `content` text NOT NULL COMMENT '内容',
  `introduce` text COMMENT '首页介绍',
  `url` varchar(128) DEFAULT NULL COMMENT '视频，或者图片链接',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站设置管理';


alter table m_website.site_banner change `type` `type` enum('PC','H5','APP','XXC','BLOG') NOT NULL DEFAULT 'PC' COMMENT '类型';

CREATE TABLE m_website.dic_notice_type (
  `id` bigint(20) NOT NULL COMMENT 'id号',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `status` tinyint(2) DEFAULT '0' COMMENT '是否激活:0未激活,1:激活',
  `auth_data_code` varchar(20) DEFAULT NULL COMMENT '权限编码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(2) DEFAULT '0' COMMENT '是否删除,0:未删除,1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO m_website.dic_notice_type (`id`, `type`, `name`, `status`,`auth_data_code`,`create_time`, `update_time`, `del`) VALUES
(1290312349646881,'HELP','帮助中心',1,'DEMO','2019-07-01 12:01:14',NULL,0),
(1290312349646882,'NOTICE','公告中心',1,'DEMO','2019-08-25 13:46:48',NULL,0);


INSERT INTO m_website.`site_banner` (`id`, `url`, `name`, `href`, `enable`, `del`, `order`, `type`, `create_time`, `update_time`, `auth_data_code`) VALUES
	(1292009134358560,'http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/de2b13f654b44714add56ac4a8054c1a.jpg','1','http://www.baidu.com',1,1,0,'PC','2019-07-10 12:46:04','2021-05-29 21:02:17','DEMO'),
	(1292568646123552,'http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/0c99a6f64a4b4bc29d08b7d2050a8999.jpg','pc首页','http://www.baidu.com',0,0,0,'PC','2019-07-13 14:52:40','2021-05-29 21:02:20','DEMO'),
	(1292568694358048,'http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/3d2922bbd0994c63a33e1a2b0e163c15.jpg','banner2','',0,0,2,'PC','2019-07-13 14:53:03','2021-05-29 21:02:24','DEMO'),
	(1312426991550496,'http://java-run-blog.oss-cn-zhangjiakou.aliyuncs.com/141b61f878d442fb8c9de0385cf277f6.jpg','博客首页',NULL,0,0,0,'BLOG','2019-10-31 13:12:37','2021-05-29 21:02:28','DENO');

INSERT INTO m_website.`site_news` (`id`, `title`, `publish`, `source`, `source_url`, `pic`, `introduce`, `context`, `create_time`, `update_time`, `publish_time`, `del`, `auth_data_code`) VALUES
	(1292389673074720,'上海迪士尼就安检事件致歉 称不接受调解说法失实',1,'新浪网','https://news.sina.com.cn/s/2019-08-24/doc-ihytcitn1558269.shtml','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/918cecdd97414713b70f5f6b58e0fcf6.jpg','近期，上海迪士尼食品入园的有关规定以及安检翻包的做法，在互联网上受到质疑，并持续发酵。对此，上海迪士尼团队昨晚发发发。','<p>章子欣家在千岛湖镇清溪村永坪自然村，澎湃新闻15日下午看到，章家大门开了半扇，章子欣父亲章军盯着手机看，不时用手狠抓一下头发；奶奶坐着，头靠在桌子上；边上还有七八位亲属。章子欣家在千岛湖镇清溪村永坪自然村，澎湃新闻15日下午看到，章家大门开了半扇，章子欣父亲章军盯着手机看，不时用手狠抓一下头发；奶奶坐着，头靠在桌子上；边上还有七八位亲属。章子欣家在千岛湖镇清溪村永坪自然村，澎湃新闻15日下午看到，章家大门开了半扇，章子欣父亲章军盯着手机看，不时用手狠抓一下头发；奶奶坐着，头靠在桌子上；边上还有七八位亲属。章子欣家在千岛湖镇清溪村永坪自然村，澎湃新闻15日下午看到，章家大门开了半扇，章子欣父亲章军盯着手机看，不时用手狠抓一下头发；奶奶坐着，头靠在桌子上；边上还有七八位亲属。章子欣家在千岛湖镇清溪村永坪自然村，澎湃新闻15日下午看到，章家大门开了半扇，章子欣父亲章军盯着手机看，不时用手狠抓一下头发；奶奶坐着，头靠在桌子上；边上还有七八位亲属。章子欣家在千岛湖镇清溪村永坪自然村，澎湃新闻15日下午看到，章家大门开了半扇，章子欣父亲章军盯着手机看，不时用手狠抓一下头发；奶奶坐着，头靠在桌子上；边上还有七八位亲属。</p>','2019-07-12 15:10:19','2021-05-29 21:02:44','2019-07-14 13:55:07',0,'DEMO'),
	(1292923492630560,'中国驻加使馆回应美国务卿言论 再敦促释放孟晚舟',1,'新浪','www.baidu.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/998a08fbbf764a92a831c798b4d65352.jpg','中新社多伦多8月23日电 中国驻加拿大大使馆发言人当地时间23日回应媒体询问时重申，任何国家都应尊重中国司法主权。','<p style="text-align:start;text-indent:2em;">中新社多伦多8月23日电 中国驻加拿大大使馆发言人当地时间23日回应媒体询问时重申，任何国家都应尊重中国司法主权，不应当对中国司法主权范围内的事情指手画脚。中方再次敦促加拿大和美国认真对待中方严正立场，纠正错误，立即释放孟晚舟。</p><p style="text-align:start;text-indent:2em;">当地时间8月22日，在加拿大访问的美国国务卿蓬佩奥在同加外长弗里兰共见记者时称，两名加公民被中方“任意”拘禁与孟晚舟案有很大不同。美联社记者就此询问中国驻加使馆有何回应。</p><p></p><p style="text-align:start;text-indent:2em;">　　中国驻加使馆发言人回应说，孟晚舟事件同两名加拿大公民个案性质当然完全不同。孟晚舟事件不是一起简单的司法案件，而是美国动用国家力量，伙同其个别盟友，以莫须有的罪名对一家中国民营高科技企业进行打压。这是典型的霸凌行为。</p><p style="text-align:start;text-indent:2em;">　　发言人说，美、加个别政客在孟晚舟事件上颠倒黑白，妄图误导舆论和民众，再次充分暴露这一事件的政治属性。美、加在孟晚舟事件上的所作所为才是任意拘禁中国公民，两国滥用双边引渡条约，严重侵犯中国公民合法权益，完全是出于政治目的。我们再次敦促加、美认真对待中方严正立场，纠正错误，立即释放孟晚舟女士，确保她平安回到中国。</p><p style="text-align:start;text-indent:2em;">　　发言人指出，康明凯、迈克尔两名加拿大公民系因涉嫌危害中国国家安全犯罪被依法逮捕。中方再次重申，中国是法治国家，中国司法机关依法独立办案。任何国家都应尊重中国司法主权，不应当对中国司法主权范围内的事情指手画脚。（完）</p><p>点击进入专题</p>','2019-07-15 13:52:44','2021-05-29 21:02:50','2019-07-15 13:53:00',0,'DEMO'),
	(1292923960295456,'河南省副省长徐光被查 曾力推周口“平坟”引关注',1,'新闻网','https://news.sina.com.cn/c/2019-08-24/doc-ihytcitn1526238.shtml','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/311ce5e6d3fe466f91cc23389662d576.jpg','章子欣家在千岛湖镇清溪村永坪自然村，家人准备将其遗体火化后海葬家人准备将其遗体火化后海葬澎湃新闻15日下午看到。\n','<p style="text-align:start;text-indent:2em;">徐光曾长期在鹤壁市任职，曾任共青团鹤壁市委书记，淇县县长、县委书记，鹤壁市委常委、秘书长、鹤壁市常务副市长，2001年他任安阳市委常委、市政府常务副市长。</p><p style="text-align:start;text-indent:2em;">　　2003年徐光开始任职周口市委副书记，2006年任周口市长，2011年任周口市委书记，先后主政周口长达11年时间。2017年1月在河南省政府换届中，徐光当选为河南省副省长，由此晋升副部级。 </p><p style="text-align:start;text-indent:2em;">　　记者注意到，徐光任职周口市时，曾力推“平坟复耕”，引发舆论关注。</p><p style="text-align:start;text-indent:2em;">　　在徐光被查之前，已有多名周口在职官员或长期任职周口的官员被查。其中包括周口市扶沟县委书记卢伟，周口市委常委、宣传部长王田业，河南省司法厅党委委员、副厅长（曾先后任周口下辖的项城市长、市委书记），周口市商水县委书记马卫东，河南省交通运输厅副厅长杨廷俊（曾任周口市副市长，鹿邑县委书记），周口市人民检察院检察长高德友等人。他们与徐光有过长期共事经历。</p><p style="text-align:start;text-indent:2em;">　　据河南省政府官网显示，徐光最近一次出席活动是在8月16日，他带领省直有关部门负责同志到部分省属重点项目施工现场，督导推进项目建设工作。</p><p style="text-align:start;text-indent:2em;">　　在副省长中，徐光排名第三，负责自然资源、住房和城乡建设、交通、重点项目建设方面工作。分管省自然资源厅、省住房和城乡建设厅、省交通运输厅、省人民防空办公室、省有色金属地质矿产局、省地质矿产勘查开发局、省林业局。联系中国铁路郑州局集团有限公司、省邮政管理局、省邮政公司、民航河南安全监督管理局、南航河南航空有限公司及各民航机场（大型企业）。</p><p style="text-align:start;text-indent:2em;">　　随着徐光被查，十八大后河南落马的省部级官员已达6人，另外5人是：河南省人大常委会原副主任秦玉海（已获有期徒刑十三年半），河南省委原常委、洛阳市委原书记陈雪枫（已获刑无期），河南省委原常委、政法委原书记吴天君（已获有期徒刑十一年），河南省人大常委会原副主任王铁（主动投案，被开除党籍处分，降为副处级非领导职务，办理退休手续），河南省政协原副主席靳绥东 （被控受贿4434余万元，尚未宣判）。</p><p style="text-align:start;text-indent:2em;"> <strong>徐光简历　　</strong></p><p style="text-align:start;text-indent:2em;">　　徐光，男，1960年6月生，汉族，湖北当阳人，在职研究生学历，天津大学经济系研究生毕业，1984年6月加入中国共产党，1977年8月参加工作。现任河南省人民政府副省长。</p><p style="text-align:start;text-indent:2em;">　　1977.08—1979.09，河南省鹤壁市石林公社下乡；</p><p style="text-align:start;text-indent:2em;">　　1979.09—1981.07，鹤壁师范学校中文专业学习；</p><p style="text-align:start;text-indent:2em;">　　1981.07—1985.04，河南省鹤壁市教育局干事、团委副书记；</p><p style="text-align:start;text-indent:2em;">　　1985.04—1988.05，河南省鹤壁市委组织部组织科干事、副科级组织员、青干科副科长；</p><p style="text-align:start;text-indent:2em;">　　1988.05—1992.11，共青团河南省鹤壁市委副书记、书记、党组书记、市青联主席；</p><p style="text-align:start;text-indent:2em;">　　1992.11—1996.03，中共河南省淇县县委副书记、县长、县委书记（1992.02—1993.12 天津大学管理系管理工程专业工商管理方向在职研究生学习）；</p><p style="text-align:start;text-indent:2em;">　　1996.03—1997.11，中共河南省鹤壁市委常委、淇县县委书记；</p><p></p><p style="text-align:start;text-indent:2em;">　　1997.11—2001.03，中共河南省鹤壁市委常委、秘书长，市人民政府常务副市长；</p><p style="text-align:start;text-indent:2em;">　　2001.03—2003.12，中共河南省安阳市委常委、市政府常务副市长；</p><p style="text-align:start;text-indent:2em;">　　2003.12—2006.12，中共河南省周口市委副书记；</p><p style="text-align:start;text-indent:2em;">　　2006.12—2011.12，中共河南省周口市委副书记、市政府市长；</p><p style="text-align:start;text-indent:2em;">　　2011.12—2015.04，中共周口市委书记；</p><p style="text-align:start;text-indent:2em;">　　2015.04—2017.01，中共周口市委书记、市人大常委会主任；</p><p style="text-align:start;text-indent:2em;">　　2017.01—2017.02，河南省人民政府副省长、中共周口市委书记、市人大常委会主任；</p><p style="text-align:start;text-indent:2em;">　　2017.02—2017.06，河南省人民政府副省长、周口市人大常委会主任；</p><p style="text-align:start;text-indent:2em;">　　2017.06— 河南省人民政府副省长。</p><p style="text-align:start;text-indent:2em;">　　第十一届全国人大代表，党的十八大代表，九届、十届省委委员</p>','2019-07-15 13:56:27','2021-05-29 21:02:54','2019-07-15 13:58:43',0,'DEMO'),
	(1292928490143776,'国防部回应美方售台武器：要求美方撤销军售计划',1,'新浪网','www.baidu.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/8cc4ccb642734afba9a9db99c332131f.jpg','章子欣家在千岛湖镇清溪村永坪自然村，家人准备将其遗体火化后海葬家人准备将其遗体火化后海葬澎湃新闻15日下午看到。\n','<p style="text-align:start;text-indent:2em;">新京报快讯 据国防部新闻局官方微博消息，8月18日，美国总统特朗普在回应媒体提问时表示，他已批准一项价值80亿美元的F-16V战机对台军售案。中国军队对此表示强烈不满和坚决反对，已向美方提出严正交涉和抗议。</p><p style="text-align:start;text-indent:2em;">　　台湾是中国不可分割的一部分。美方向台出售武器严重违反一个中国原则和中美三个联合公报规定，严重干涉中国内政，严重损害中国主权和安全利益，严重危害中美两国两军关系发展，严重损害台海地区和平稳定，是完全错误的，也是非常危险的。</p><p style="text-align:start;text-indent:2em;">　　台湾问题事关中国主权和领土完整，不容任何外来干涉。任何分裂中国的图谋都不可能得逞，任何对中国军队决心意志的低估都极其危险。中方要求美方充分认识到美售台武器问题的严重危害性，认清形势，立即撤销上述对台军售计划，立即停止美台军事联系，以免给中美两国两军关系和台海和平稳定造成严重损害。中国军队有坚定的意志、充分的信心、足够的能力挫败任何形式的外部势力干涉和“台独”分裂行径，坚定捍卫国家主权统一和领土完整，坚定维护台海地区和平稳定。</p><p style="text-align:start;text-indent:2em;">　　来源：国防部新闻局官方微博</p><p><br/></p>','2019-07-15 14:32:27','2021-05-29 21:02:57','2019-07-15 14:32:32',0,'DEMO');

INSERT INTO m_website.`site_notice` (`id`, `type`, `title`, `context`, `publish`, `create_time`, `update_time`, `del`, `ordering`, `click_count`, `publish_time`, `auth_data_code`) VALUES
	(1393313284358176,'HELP','文章帮助中心','<p>文章帮助中心文章帮助中心文章帮助中心</p>',1,'2021-01-19 22:59:08',NULL,0,1,0,'2021-01-19 23:07:13','DEMO'),
	(1395209550168096,'NOTICE','公告','<p>111111111111</p>',1,'2021-01-30 10:09:17',NULL,0,1,0,'2021-01-30 10:09:21','DEMO');


INSERT INTO m_website.`site_partner` (`id`, `name`, `href`, `pic_url`, `pc_show`, `m_show`, `ordering`, `del`, `create_time`, `update_time`, `auth_data_code`) VALUES
	(1293720282464288,'G公司','https://www.a.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/227b2cfbaf764fd1a4230c3a7a1b7e1c.png',0,0,NULL,0,'2019-07-19 23:25:03','2021-05-29 21:03:15','DEMO'),
	(1293720311824416,'F公司','https://www.a.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/c1d3fa7206134257b987e43cc8d8cfa7.png',0,0,NULL,0,'2019-07-19 23:25:17','2021-05-29 21:03:18','DEMO'),
	(1293720334893088,'E公司','https://www.a.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/526a1f3178ef4367bfbfc15e64d37ac0.png',0,0,NULL,0,'2019-07-19 23:25:28','2021-05-29 21:03:22','DEMO'),
	(1293720360058912,'D公司','https://www.a.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/9b97fcc3c4d847698c5aea3c077c5efd.png',0,0,NULL,0,'2019-07-19 23:25:40','2021-05-29 21:03:25','DEMO'),
	(1293727626690592,'C公司','https://www.c.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/d75725b7790e46d682c0df831cc49705.png',0,0,NULL,0,'2019-07-20 00:23:25','2021-05-29 21:03:28','DEMO'),
	(1293727653953568,'B公司','https://www.b.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/0af6f781654641cfb95a21d261c77ae4.png',0,0,NULL,0,'2019-07-20 00:23:38','2021-05-29 21:03:31','DEMO'),
	(1293727670730784,'A公司','https://www.a.com','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/b6cc317bff7d4b14ae1641aa1b64a641.png',0,0,NULL,0,'2019-07-20 00:23:46','2021-05-29 21:03:35','DEMO');


INSERT INTO m_website.`site_produce` (`id`, `title`, `file_list`, `cover_image`, `pc_show`, `m_show`, `home_show`, `ordering`, `create_time`, `update_time`, `del`, `introduce`, `auth_data_code`) VALUES
	(1291829190328351,'HTML/CSS','[{"uid":"rc-upload-1566513652976-41","url":"http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/97f723dc5123471997a89075aebcfb63.png"}]','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/e9e17bbacdcc49a89106ccc068e1ce57.png',1,1,1,2,'2019-07-10 13:29:32',NULL,0,'HTML/CSS','DEMO'),
	(1291830209544224,'Spring教程','[{"uid":"rc-upload-1566513652976-47","url":"http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/4fabe5e3c11b47249c5e7b3ad0c2b1c0.png"}]','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/ec572586a18043de858dcea40d2dd6f0.png',1,1,1,1,'2019-07-09 13:04:06',NULL,0,'Spring教程','DEMO'),
	(1292680942321696,'JAVA教程设计','[{"uid":"rc-upload-1566637759423-34","url":"http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/c978a9c07f504c5e8bb449027d0aa834.jpg"},{"uid":"rc-upload-1566637759423-39","url":"http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/d23d9ba08c0649da8eb0c791685eded0.jpg"}]','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/55de2f36dc1648ed9b2f9cff97971b8d.png',1,1,1,1,'2019-07-14 05:45:07',NULL,0,'写好每一行代码，做好每张设计图的解析。','DEMO'),
	(1292681374335008,'web网站设计','[{"uid":"rc-upload-1566637759423-43","url":"http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/68840700bc964c87baaac7c688fdf519.jpg"}]','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/6cdea898764246aab372fec731d466e0.png',1,1,1,3,'2019-07-14 05:48:33',NULL,0,'十年最基础的前端开发经验，让最简单的知识变得细腻，让最基础的标签显得灵动。','DEMO');


INSERT INTO m_website.`site_produce_detail` (`id`, `produce_id`, `context`, `create_time`, `update_time`, `del`, `auth_data_code`) VALUES
	(1292023860559904,1291830209544224,'<h1></h1><h1 style="text-align:center;"><span style="font-size:32px"><span style="background-color:#f9f9f9"><span style="color:#000000">8个无版权限制的视频素材网站</span></span></span></h1><p></p><p><span style="font-size:14px">一图顶千言，而一分钟的视频效果相当于1800万个文字。相比文字和图片，视频这种多媒体形式在有效吸引用户视线、增强用户理解等方面的能力是毋庸置疑的。这也是为什么随着网络带宽和移动设备性能的不断提升，越来越多的网站开始将视频作为主要的营销方式之一。不过，对于不少个人站长和中小企业来说，自行制作剪辑视频并不现实（技术难度大、时间成本高），怎样才能获得专业、高清的视频素材呢？今天小飞就整理了8个可免费下载的视频素材站，快来一起看看吧！</span></p><p></p><h2><strong><em><a href="https://www.pexels.com/" target="">Pexels</a></em></strong></h2><p>Pexels 是一个集大成的素材站，网站上有很多无版权的图片和视频，甚至还集成了其他站点的众多资源。打开Pexels网站，找到右上方菜单栏中的“Free Videos”，您就可以看到不同分类的视频了。将鼠标悬停在视频上即可进行预览，点击进入视频页面还能看到相似视频的推荐。Pexels 上的视频均为 HD 高清格式，无需注册即可下载。</p><p></p><h2><strong><em><a href="https://pixabay.com/en/" target="_blank" rel="nofollow">Pixabay</a></em></strong></h2><p>Pixabay也是一个老牌的素材站，事实上 Pexels 上的很多资源都来自于它。这个网站上的免费图片和视频有150万之多，均由社区内的用户提供，可免注册直接下载。当然，如果您十分欣赏某个作品，可以进入该用户的个人主页，进行关注、点赞或打赏。除了图片和视频以外，Pixabay还提供可免费下载的插画、矢量图等其他素材。<br/><br/><strong><a href="http://www.wedistill.io/videos" target="_blank" rel="nofollow" url-flagtarget="_blank">Distill</a></strong><br/>Distill是一个专门提供创意短视频的网站平台，其高清视频也都能免费下载、用于个人或商业项目。与上面两个网站相比，Distill上的视频数量可能并没有那么多。这里的视频均由认证作者上传，每10天更新一次（每次10个视频）。不同作者创作出的视频风格各有差异，偶尔上去看看找点灵感还是不错的。由于更新频率不高，您可以选择订阅它的 Email 推送。</p><p></p><p><u><strong><a href="https://www.pexels.com" target="">Videvo</a></strong></u></p><p>Videvo是一个专门提供视频素材和 Motion Graphics 动画素材（比如我们熟悉的电影开头倒计时等）的平台，网站上的所有素材都可以免费下载。不过这里需要注意， Videvo上的不同素材遵循着3种不同的协议：Royalty Free License， Videvo Attribution License 和 CC3。   第一种协议对版权几乎不作限制，而后两种虽然也允许用户免费进行视频下载、编辑，但要求在使用时署名原作者。所以，当您在网站上看到喜欢的视频想要使用时，最好看一下它们的&quot;License Information&quot;。</p><p></p><p><u><strong><a href="http://www.lifeofvids.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Life of Vids</a></strong></u></p><p>Life of Vids（无版权视频网站）。它们都隶属于加拿大的LEEROY创意工作室，Life of Vids上的所有视频也都是由该工作室拍摄并上传的。也许是由于工作室的偏好原因，相比起别家的唯美视频，这个网站上有不少偏生活化的视频场景，比如港口作业、大型机械工作等。网站上的视频均可用于个人或商业用途，不过LEEROY规定，一个网站上不能同时使用它的视频超过10个。</p><p></p><p><strong><a href="http://vidlery.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Vidlery</a></strong></p><p>Vidlery 则是一个专注动画视频的网站。视频风格多样，不过大部分画风较为简单，数量也十分有限，比较推荐用作主页背景。它还非常贴心地提供了将其动画视频放到自己网站上的教程。</p><p style="text-align:start;text-indent:2em;"><br/><strong><a href="http://mazwai.com/#/grid" target="_blank" rel="nofollow" url-flagtarget="_blank">Mazwai</a></strong></p><p>Mazwai是一个新兴的视频站，他们会挑选最优秀的免费视频发布出来。网站旨在帮助设计师、艺术家和创意工作者获取高品质的视频资源，帮助他们设计出更优秀的产品或者作品。不过，网站上所有视频是依据CC3.0协议的，也就是说，您在使用的时候需要署名原作者。</p><p></p><p><strong><a href="https://www.videezy.com/" target="_blank" rel="nofollow">Videezy</a></strong></p><p>Videezy 是一个摄影师视频素材分享社区，其中有的素材是来自专业摄影师的，也有不少是来自业余摄影爱好者的。无需注册，您就可以在这获得高品质的视频素材。</p><p><br/></p>','2019-07-10 14:43:06','2019-08-24 17:31:50',0,'DEMO'),
	(1292024124801056,1291830045966368,'<ul><li>asas s</li></ul>','2019-07-10 14:45:12',NULL,0,'DEMO'),
	(1300118907125792,1292681374335008,'<h1></h1><h1 style="text-align:center;"><span style="font-size:32px"><span style="background-color:#f9f9f9"><span style="color:#000000">8个无版权限制的视频素材网站</span></span></span></h1><p></p><p><span style="font-size:14px">一图顶千言，而一分钟的视频效果相当于1800万个文字。相比文字和图片，视频这种多媒体形式在有效吸引用户视线、增强用户理解等方面的能力是毋庸置疑的。这也是为什么随着网络带宽和移动设备性能的不断提升，越来越多的网站开始将视频作为主要的营销方式之一。不过，对于不少个人站长和中小企业来说，自行制作剪辑视频并不现实（技术难度大、时间成本高），怎样才能获得专业、高清的视频素材呢？今天小飞就整理了8个可免费下载的视频素材站，快来一起看看吧！</span></p><p></p><h2><strong><em><a href="https://www.pexels.com/" target="">Pexels</a></em></strong></h2><p>Pexels 是一个集大成的素材站，网站上有很多无版权的图片和视频，甚至还集成了其他站点的众多资源。打开Pexels网站，找到右上方菜单栏中的“Free Videos”，您就可以看到不同分类的视频了。将鼠标悬停在视频上即可进行预览，点击进入视频页面还能看到相似视频的推荐。Pexels 上的视频均为 HD 高清格式，无需注册即可下载。</p><p></p><h2><strong><em><a href="https://pixabay.com/en/" target="_blank" rel="nofollow">Pixabay</a></em></strong></h2><p>Pixabay也是一个老牌的素材站，事实上 Pexels 上的很多资源都来自于它。这个网站上的免费图片和视频有150万之多，均由社区内的用户提供，可免注册直接下载。当然，如果您十分欣赏某个作品，可以进入该用户的个人主页，进行关注、点赞或打赏。除了图片和视频以外，Pixabay还提供可免费下载的插画、矢量图等其他素材。<br/><br/><strong><a href="http://www.wedistill.io/videos" target="_blank" rel="nofollow" url-flagtarget="_blank">Distill</a></strong><br/>Distill是一个专门提供创意短视频的网站平台，其高清视频也都能免费下载、用于个人或商业项目。与上面两个网站相比，Distill上的视频数量可能并没有那么多。这里的视频均由认证作者上传，每10天更新一次（每次10个视频）。不同作者创作出的视频风格各有差异，偶尔上去看看找点灵感还是不错的。由于更新频率不高，您可以选择订阅它的 Email 推送。</p><p></p><p><u><strong><a href="https://www.pexels.com" target="">Videvo</a></strong></u></p><p>Videvo是一个专门提供视频素材和 Motion Graphics 动画素材（比如我们熟悉的电影开头倒计时等）的平台，网站上的所有素材都可以免费下载。不过这里需要注意， Videvo上的不同素材遵循着3种不同的协议：Royalty Free License， Videvo Attribution License 和 CC3。   第一种协议对版权几乎不作限制，而后两种虽然也允许用户免费进行视频下载、编辑，但要求在使用时署名原作者。所以，当您在网站上看到喜欢的视频想要使用时，最好看一下它们的&quot;License Information&quot;。</p><p></p><p><u><strong><a href="http://www.lifeofvids.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Life of Vids</a></strong></u></p><p>Life of Vids（无版权视频网站）。它们都隶属于加拿大的LEEROY创意工作室，Life of Vids上的所有视频也都是由该工作室拍摄并上传的。也许是由于工作室的偏好原因，相比起别家的唯美视频，这个网站上有不少偏生活化的视频场景，比如港口作业、大型机械工作等。网站上的视频均可用于个人或商业用途，不过LEEROY规定，一个网站上不能同时使用它的视频超过10个。</p><p></p><p><strong><a href="http://vidlery.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Vidlery</a></strong></p><p>Vidlery 则是一个专注动画视频的网站。视频风格多样，不过大部分画风较为简单，数量也十分有限，比较推荐用作主页背景。它还非常贴心地提供了将其动画视频放到自己网站上的教程。</p><p style="text-align:start;text-indent:2em;"><br/><strong><a href="http://mazwai.com/#/grid" target="_blank" rel="nofollow" url-flagtarget="_blank">Mazwai</a></strong></p><p>Mazwai是一个新兴的视频站，他们会挑选最优秀的免费视频发布出来。网站旨在帮助设计师、艺术家和创意工作者获取高品质的视频资源，帮助他们设计出更优秀的产品或者作品。不过，网站上所有视频是依据CC3.0协议的，也就是说，您在使用的时候需要署名原作者。</p><p></p><p><strong><a href="https://www.videezy.com/" target="_blank" rel="nofollow">Videezy</a></strong></p><p>Videezy 是一个摄影师视频素材分享社区，其中有的素材是来自专业摄影师的，也有不少是来自业余摄影爱好者的。无需注册，您就可以在这获得高品质的视频素材。</p><p><br/></p>','2019-08-24 06:56:45','2019-08-24 17:31:19',0,'DEMO'),
	(1300136124743712,1292680942321696,'<h1></h1><h1 style="text-align:center;"><span style="font-size:32px"><span style="background-color:#f9f9f9"><span style="color:#000000">8个无版权限制的视频素材网站</span></span></span></h1><p></p><p><span style="font-size:14px">一图顶千言，而一分钟的视频效果相当于1800万个文字。相比文字和图片，视频这种多媒体形式在有效吸引用户视线、增强用户理解等方面的能力是毋庸置疑的。这也是为什么随着网络带宽和移动设备性能的不断提升，越来越多的网站开始将视频作为主要的营销方式之一。不过，对于不少个人站长和中小企业来说，自行制作剪辑视频并不现实（技术难度大、时间成本高），怎样才能获得专业、高清的视频素材呢？今天小飞就整理了8个可免费下载的视频素材站，快来一起看看吧！</span></p><p></p><h2><strong><em><a href="https://www.pexels.com/" target="">Pexels</a></em></strong></h2><p>Pexels 是一个集大成的素材站，网站上有很多无版权的图片和视频，甚至还集成了其他站点的众多资源。打开Pexels网站，找到右上方菜单栏中的“Free Videos”，您就可以看到不同分类的视频了。将鼠标悬停在视频上即可进行预览，点击进入视频页面还能看到相似视频的推荐。Pexels 上的视频均为 HD 高清格式，无需注册即可下载。</p><p></p><h2><strong><em><a href="https://pixabay.com/en/" target="_blank" rel="nofollow">Pixabay</a></em></strong></h2><p>Pixabay也是一个老牌的素材站，事实上 Pexels 上的很多资源都来自于它。这个网站上的免费图片和视频有150万之多，均由社区内的用户提供，可免注册直接下载。当然，如果您十分欣赏某个作品，可以进入该用户的个人主页，进行关注、点赞或打赏。除了图片和视频以外，Pixabay还提供可免费下载的插画、矢量图等其他素材。<br/><br/><strong><a href="http://www.wedistill.io/videos" target="_blank" rel="nofollow" url-flagtarget="_blank">Distill</a></strong><br/>Distill是一个专门提供创意短视频的网站平台，其高清视频也都能免费下载、用于个人或商业项目。与上面两个网站相比，Distill上的视频数量可能并没有那么多。这里的视频均由认证作者上传，每10天更新一次（每次10个视频）。不同作者创作出的视频风格各有差异，偶尔上去看看找点灵感还是不错的。由于更新频率不高，您可以选择订阅它的 Email 推送。</p><p></p><p><u><strong><a href="https://www.pexels.com" target="">Videvo</a></strong></u></p><p>Videvo是一个专门提供视频素材和 Motion Graphics 动画素材（比如我们熟悉的电影开头倒计时等）的平台，网站上的所有素材都可以免费下载。不过这里需要注意， Videvo上的不同素材遵循着3种不同的协议：Royalty Free License， Videvo Attribution License 和 CC3。   第一种协议对版权几乎不作限制，而后两种虽然也允许用户免费进行视频下载、编辑，但要求在使用时署名原作者。所以，当您在网站上看到喜欢的视频想要使用时，最好看一下它们的&quot;License Information&quot;。</p><p></p><p><u><strong><a href="http://www.lifeofvids.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Life of Vids</a></strong></u></p><p>Life of Vids（无版权视频网站）。它们都隶属于加拿大的LEEROY创意工作室，Life of Vids上的所有视频也都是由该工作室拍摄并上传的。也许是由于工作室的偏好原因，相比起别家的唯美视频，这个网站上有不少偏生活化的视频场景，比如港口作业、大型机械工作等。网站上的视频均可用于个人或商业用途，不过LEEROY规定，一个网站上不能同时使用它的视频超过10个。</p><p></p><p><strong><a href="http://vidlery.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Vidlery</a></strong></p><p>Vidlery 则是一个专注动画视频的网站。视频风格多样，不过大部分画风较为简单，数量也十分有限，比较推荐用作主页背景。它还非常贴心地提供了将其动画视频放到自己网站上的教程。</p><p style="text-align:start;text-indent:2em;"><br/><strong><a href="http://mazwai.com/#/grid" target="_blank" rel="nofollow" url-flagtarget="_blank">Mazwai</a></strong></p><p>Mazwai是一个新兴的视频站，他们会挑选最优秀的免费视频发布出来。网站旨在帮助设计师、艺术家和创意工作者获取高品质的视频资源，帮助他们设计出更优秀的产品或者作品。不过，网站上所有视频是依据CC3.0协议的，也就是说，您在使用的时候需要署名原作者。</p><p></p><p><strong><a href="https://www.videezy.com/" target="_blank" rel="nofollow">Videezy</a></strong></p><p>Videezy 是一个摄影师视频素材分享社区，其中有的素材是来自专业摄影师的，也有不少是来自业余摄影爱好者的。无需注册，您就可以在这获得高品质的视频素材。</p><p><br/></p>','2019-08-24 09:13:35','2019-08-24 17:31:36',0,'DEMO'),
	(1300138402250784,1291829190328351,'<h1></h1><h1 style="text-align:center;"><span style="font-size:32px"><span style="background-color:#f9f9f9"><span style="color:#000000">8个无版权限制的视频素材网站</span></span></span></h1><p></p><p><span style="font-size:14px">一图顶千言，而一分钟的视频效果相当于1800万个文字。相比文字和图片，视频这种多媒体形式在有效吸引用户视线、增强用户理解等方面的能力是毋庸置疑的。这也是为什么随着网络带宽和移动设备性能的不断提升，越来越多的网站开始将视频作为主要的营销方式之一。不过，对于不少个人站长和中小企业来说，自行制作剪辑视频并不现实（技术难度大、时间成本高），怎样才能获得专业、高清的视频素材呢？今天小飞就整理了8个可免费下载的视频素材站，快来一起看看吧！</span></p><p></p><h2><strong><em><a href="https://www.pexels.com/" target="">Pexels</a></em></strong></h2><p>Pexels 是一个集大成的素材站，网站上有很多无版权的图片和视频，甚至还集成了其他站点的众多资源。打开Pexels网站，找到右上方菜单栏中的“Free Videos”，您就可以看到不同分类的视频了。将鼠标悬停在视频上即可进行预览，点击进入视频页面还能看到相似视频的推荐。Pexels 上的视频均为 HD 高清格式，无需注册即可下载。</p><p></p><h2><strong><em><a href="https://pixabay.com/en/" target="_blank" rel="nofollow">Pixabay</a></em></strong></h2><p>Pixabay也是一个老牌的素材站，事实上 Pexels 上的很多资源都来自于它。这个网站上的免费图片和视频有150万之多，均由社区内的用户提供，可免注册直接下载。当然，如果您十分欣赏某个作品，可以进入该用户的个人主页，进行关注、点赞或打赏。除了图片和视频以外，Pixabay还提供可免费下载的插画、矢量图等其他素材。<br/><br/><strong><a href="http://www.wedistill.io/videos" target="_blank" rel="nofollow" url-flagtarget="_blank">Distill</a></strong><br/>Distill是一个专门提供创意短视频的网站平台，其高清视频也都能免费下载、用于个人或商业项目。与上面两个网站相比，Distill上的视频数量可能并没有那么多。这里的视频均由认证作者上传，每10天更新一次（每次10个视频）。不同作者创作出的视频风格各有差异，偶尔上去看看找点灵感还是不错的。由于更新频率不高，您可以选择订阅它的 Email 推送。</p><p></p><p><u><strong><a href="https://www.pexels.com" target="">Videvo</a></strong></u></p><p>Videvo是一个专门提供视频素材和 Motion Graphics 动画素材（比如我们熟悉的电影开头倒计时等）的平台，网站上的所有素材都可以免费下载。不过这里需要注意， Videvo上的不同素材遵循着3种不同的协议：Royalty Free License， Videvo Attribution License 和 CC3。   第一种协议对版权几乎不作限制，而后两种虽然也允许用户免费进行视频下载、编辑，但要求在使用时署名原作者。所以，当您在网站上看到喜欢的视频想要使用时，最好看一下它们的&quot;License Information&quot;。</p><p></p><p><u><strong><a href="http://www.lifeofvids.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Life of Vids</a></strong></u></p><p>Life of Vids（无版权视频网站）。它们都隶属于加拿大的LEEROY创意工作室，Life of Vids上的所有视频也都是由该工作室拍摄并上传的。也许是由于工作室的偏好原因，相比起别家的唯美视频，这个网站上有不少偏生活化的视频场景，比如港口作业、大型机械工作等。网站上的视频均可用于个人或商业用途，不过LEEROY规定，一个网站上不能同时使用它的视频超过10个。</p><p></p><p><strong><a href="http://vidlery.com/" target="_blank" rel="nofollow" url-flagtarget="_blank">Vidlery</a></strong></p><p>Vidlery 则是一个专注动画视频的网站。视频风格多样，不过大部分画风较为简单，数量也十分有限，比较推荐用作主页背景。它还非常贴心地提供了将其动画视频放到自己网站上的教程。</p><p style="text-align:start;text-indent:2em;"><br/><strong><a href="http://mazwai.com/#/grid" target="_blank" rel="nofollow" url-flagtarget="_blank">Mazwai</a></strong></p><p>Mazwai是一个新兴的视频站，他们会挑选最优秀的免费视频发布出来。网站旨在帮助设计师、艺术家和创意工作者获取高品质的视频资源，帮助他们设计出更优秀的产品或者作品。不过，网站上所有视频是依据CC3.0协议的，也就是说，您在使用的时候需要署名原作者。</p><p></p><p><strong><a href="https://www.videezy.com/" target="_blank" rel="nofollow">Videezy</a></strong></p><p>Videezy 是一个摄影师视频素材分享社区，其中有的素材是来自专业摄影师的，也有不少是来自业余摄影爱好者的。无需注册，您就可以在这获得高品质的视频素材。</p><p><br/></p>','2019-08-24 09:31:41',NULL,0,'DEMO');


INSERT INTO m_website.`site_setting_detail` (`id`, `type`, `content`, `introduce`, `url`, `create_time`, `update_time`, `del`, `auth_data_code`) VALUES
	(1300171690344480,'ABOUT_US','<h1 style="text-align:center;" size="0" _root="undefined" __ownerid="undefined" __hash="undefined" __altered="false">ABC有限公司</h1><p style="text-align:left;" size="5" _root="[object Object]" __ownerid="undefined" __hash="undefined" __altered="false">ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。</p><p></p><p style="text-indent:2em;"></p><div class="media-wrap image-wrap align-center" style="text-align:center"><img class="media-wrap image-wrap align-center" src="http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/ece778139d82492da02528f830c8a7e7.jpg"/></div><p></p><p></p><p style="text-align:left;" size="5" _root="[object Object]" __ownerid="undefined" __hash="undefined" __altered="false">ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。</p><p></p><p></p><p style="text-align:left;" size="5" _root="[object Object]" __ownerid="undefined" __hash="undefined" __altered="false">ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。</p><p></p><p></p><div class="media-wrap image-wrap align-center" style="text-align:center"><img class="media-wrap image-wrap align-center" src="http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/faf567d2d49348fcb7f9a4a1c7dd70fa.jpg"/></div><p></p><p></p><p style="text-align:left;" size="5" _root="[object Object]" __ownerid="undefined" __hash="undefined" __altered="false">ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。</p><p></p><p style="text-align:left;" size="5" _root="[object Object]" __ownerid="undefined" __hash="undefined" __altered="false">ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。</p>','ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司ABC有限公司。','http://zrcaifu-vip-test-oss.oss-cn-beijing.aliyuncs.com/79cdb13c149b4594ab74938417dd2f7f.mp4','2019-08-24 13:56:14','2019-08-24 15:45:05',0,'DEMO'),
	(1300174120943648,'COMPANY','<p>企业文化</p>',NULL,NULL,'2019-08-24 14:15:33','2019-08-24 14:31:30',0,'DEMO'),
	(1300176148889632,'WANT','<p>公司愿景</p>',NULL,NULL,'2019-08-24 14:31:40',NULL,0,'DEMO'),
	(1300176171958304,'DEVELOP','<p><span style="color:#000000"><span style="font-size:14px"><span style="background-color:#ffffff">发展历程</span></span></span></p>',NULL,NULL,'2019-08-24 14:31:51',NULL,0,'DEMO'),
	(1300184805933088,'CONTACT_US','<p>联系我们</p>',NULL,NULL,'2019-08-24 15:40:28',NULL,0,'DEMO');
