create database khome_pro character set utf8;
#创建文章表
CREATE TABLE `khome_pro`.`ARTICLE` (
  `ARTICLE_ID` VARCHAR(32) NOT NULL COMMENT '文章ID',
  `TITLE` VARCHAR(100) NULL COMMENT '标题',
  `BRIEF` VARCHAR(3000) NULL COMMENT '内容',
  `CREATE_TIME` DATETIME NULL COMMENT '创建时间',
  `CATEGORY` VARCHAR(50) NULL COMMENT '分类',
  PRIMARY KEY (`ARTICLE_ID`))
COMMENT = '文章表';

#添加摘要字段并将CREATE_TIME和CATEGORY字段设置为非空
ALTER TABLE `khome_pro`.`ARTICLE` 
CHANGE COLUMN `CREATE_TIME` `CREATE_TIME` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `CATEGORY` `CATEGORY` VARCHAR(50) NOT NULL COMMENT '分类' ,
ADD COLUMN `SUMMARY` VARCHAR(200) NULL AFTER `CATEGORY`;

#增加查阅次数列
ALTER TABLE `khome_pro`.`ARTICLE` 
CHANGE COLUMN `SUMMARY` `SUMMARY` VARCHAR(200) NULL COMMENT '摘要' ,
ADD COLUMN `READ_COUNT` BIGINT(255) NULL COMMENT '查阅次数' AFTER `SUMMARY`;

#创建图片表
CREATE TABLE `khome_pro`.`BRIEF_PIC` (
  `PIC_ID` VARCHAR(32) NOT NULL COMMENT '图片ID',
  `ARTICLE_ID` VARCHAR(32) NOT NULL COMMENT '文章ID',
  `PIC_PATH` VARCHAR(100) NULL COMMENT '图片路径',
  `PIC_TITLE` VARCHAR(200) NULL COMMENT '图片标题',
  `SORT_ID` INT NULL COMMENT '序号',
  PRIMARY KEY (`PIC_ID`),
  UNIQUE INDEX `IDX_ARTICLE_SORT` (`ARTICLE_ID` ASC, `SORT_ID` ASC))
ENGINE = INNODB
COMMENT = '图片内容';
ALTER TABLE `khome_pro`.`BRIEF_PIC` 
DROP COLUMN `PIC_TITLE`;

#创建信息记录表
CREATE TABLE `khome_pro`.`MESSAGE` (
  `MSG_ID` VARCHAR(32) NOT NULL COMMENT '消息ID',
  `MSG_CODE` VARCHAR(45) NULL COMMENT '消息编码',
  `MSG_INFO` VARCHAR(2000) NULL COMMENT '消息内容',
  `CREATE_TIME` DATE NULL COMMENT '创建时间',
  PRIMARY KEY (`MSG_ID`))
COMMENT = '文档转换信息表';

#加上标识字段
ALTER TABLE `khome_pro`.`BRIEF_PIC` 
ADD COLUMN `CUT_STATUS` INT NULL COMMENT '裁剪状态 0 未裁剪 1 已裁剪' AFTER `SORT_ID`;

#创建字典表
CREATE TABLE `khome_pro`.`DIC_COMMON` (
  `DIC_ID` VARCHAR(32) NOT NULL COMMENT '字典ID',
  `GROUP_ID` VARCHAR(45) NULL COMMENT '类型ID',
  `CODE` VARCHAR(100) NULL COMMENT '字典编码',
  `COMMENT` VARCHAR(250) NULL COMMENT '字典中文',
  PRIMARY KEY (`DIC_ID`),
  UNIQUE INDEX `IDX_GROUPID_CODE` (`GROUP_ID` ASC, `CODE` ASC)  COMMENT 'GROUP_ID和CODE的组合索引')
COMMENT = '字典表';

#创建标签表
CREATE TABLE `khome_pro`.`ARTICLE_LABEL` (
  `ARTICLE_ID` VARCHAR(32) NOT NULL COMMENT '文章ID',
  `LABEL` VARCHAR(100) NOT NULL COMMENT '标签内容',
  PRIMARY KEY (`ARTICLE_ID`,`LABEL`))
COMMENT = '文章标签表';

CREATE TABLE `khome_pro`.`UPDATED` (
  `UPDATED_ID` varchar(32) NOT NULL COMMENT '版本更新id',
  `UPDATED_INFO` varchar(2000) DEFAULT NULL COMMENT '更新信息',
  `CREATE_TIME` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`UPDATED_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本信息更新表';