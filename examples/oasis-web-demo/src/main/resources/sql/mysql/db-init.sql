DROP TABLE IF EXISTS company_info;

CREATE TABLE company_info (
  com_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  com_name VARCHAR(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名称',
  status TINYINT(2) NOT NULL COMMENT '状态:0-N/A,1-有效,2-无效',
  add_time DATETIME NOT NULL COMMENT '添加时间',
  PRIMARY KEY (com_id)
)comment='公司表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS prod_info;

CREATE TABLE prod_info (
  prod_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  com_id INTEGER(10) NOT NULL COMMENT '公司id',
  prod_name VARCHAR(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '产品名称',
  prod_alias VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '产品别名',
  prod_pic BLOB COMMENT '图片',
  limit_quantity TINYINT(2) NOT NULL DEFAULT '1' COMMENT '限购数量',
  weight TINYINT(2) NOT NULL DEFAULT 0 COMMENT '权重',
  status TINYINT(2) NOT NULL DEFAULT 0 COMMENT '状态:0-N/A,1-有效,2-无效',
  adder_id INTEGER(10) NOT NULL COMMENT '添加者id',
  updater_id INTEGER(10) NOT NULL COMMENT '修改者id',
  add_time DATETIME NOT NULL COMMENT '添加时间',
  update_time DATETIME NOT NULL COMMENT '修改时间',
  PRIMARY KEY (prod_id)
)comment='产品表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS prod_sku;

CREATE TABLE prod_sku (
  sku_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  sku_name VARCHAR(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '分类名称',
  PRIMARY KEY (sku_id)
)comment='分类表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS prod_sku_option;

CREATE TABLE prod_sku_option (
  sku_option_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  sku_id INTEGER(10) NOT NULL COMMENT '分类id',
  option_name VARCHAR(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标签名称',
  weight INTEGER(10) NOT NULL DEFAULT 0 COMMENT '权重',
  PRIMARY KEY (sku_option_id)
)comment='分类标签表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS prod_sku_link;

CREATE TABLE prod_sku_link (
  sku_link_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  prod_id INTEGER(10) NOT NULL COMMENT '产品id',
  sku_option_id INTEGER(10) NOT NULL COMMENT '标签id',
  PRIMARY KEY (sku_link_id)
)comment='产品标签关联表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS user_info;

CREATE TABLE user_info (
  user_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  email VARCHAR(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  password VARCHAR(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码',
  `type` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '角色:0-N/A,1-普通用户,2-管理员',
  `status` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '状态:0-N/A,1-有效,2-无效',
  add_time DATETIME NOT NULL COMMENT '添加时间',
  update_time DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (user_id),
  UNIQUE KEY email (email)
)comment='用户表'
ENGINE=InnoDB AUTO_INCREMENT=3 CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS appraise;

CREATE TABLE appraise (
  appraise_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  user_id INTEGER(10) NOT NULL COMMENT '用户id',
  prod_id INTEGER(10) NOT NULL COMMENT '产品id',
  content VARCHAR(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '评价内容',
  score TINYINT(2) NOT NULL DEFAULT 0 COMMENT '评分',
  add_time DATETIME NOT NULL COMMENT '添加时间',
  PRIMARY KEY (appraise_id),
  UNIQUE KEY user_prod (user_id, prod_id)
)comment='用户产品评价表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

DROP TABLE IF EXISTS favorite;

CREATE TABLE favorite (
  favorite_id INTEGER(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  user_id INTEGER(10) NOT NULL COMMENT '用户id',
  prod_id INTEGER(11) NOT NULL COMMENT '产品id',
  add_time DATETIME NOT NULL COMMENT '添加时间',
  PRIMARY KEY (favorite_id),
  UNIQUE KEY user_prod (user_id, prod_id)
)comment='用户产品收藏表'
ENGINE=InnoDB CHARACTER SET 'utf8' COLLATE 'utf8_bin';

INSERT INTO user_info (user_id, email, password, `type`, `status`, add_time, update_time) VALUES 
  (1,'lvchenggang@made-in-china.com','123456',1,1,'2014-01-01 00:00:00','2014-01-01 00:00:00');
  
INSERT INTO user_info (user_id, email, password, `type`, `status`, add_time, update_time) VALUES   
  (2,'khan@xyz.cn','khan',2,1,'2014-01-01 00:00:00','2014-01-01 00:00:00');
  
COMMIT;
