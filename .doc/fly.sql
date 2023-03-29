/*
SQLyog v10.2 
MySQL - 5.7.9 : Database - fly
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fly` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `fly`;

/*Table structure for table `act_id_bytearray` */

DROP TABLE IF EXISTS `act_id_bytearray`;

CREATE TABLE `act_id_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_bytearray` */

/*Table structure for table `act_id_priv` */

DROP TABLE IF EXISTS `act_id_priv`;

CREATE TABLE `act_id_priv` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_priv` */

/*Table structure for table `act_id_priv_mapping` */

DROP TABLE IF EXISTS `act_id_priv_mapping`;

CREATE TABLE `act_id_priv_mapping` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PRIV_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_PRIV_MAPPING` (`PRIV_ID_`),
  KEY `ACT_IDX_PRIV_USER` (`USER_ID_`),
  KEY `ACT_IDX_PRIV_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_PRIV_MAPPING` FOREIGN KEY (`PRIV_ID_`) REFERENCES `act_id_priv` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_priv_mapping` */

/*Table structure for table `act_id_property` */

DROP TABLE IF EXISTS `act_id_property`;

CREATE TABLE `act_id_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_property` */

insert  into `act_id_property`(`NAME_`,`VALUE_`,`REV_`) values ('schema.version','6.0.0.0',1);

/*Table structure for table `act_id_token` */

DROP TABLE IF EXISTS `act_id_token`;

CREATE TABLE `act_id_token` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TOKEN_VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATE_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `IP_ADDRESS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_AGENT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATA_` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_token` */

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`num`,`pid`,`pids`,`simplename`,`fullname`,`tips`,`version`) values (1,1,0,'[0],','总公司','总公司','',NULL),(2,2,1,'[0],[1],','开发部','开发部','',NULL),(3,3,1,'[0],[1],','运营部','运营部','',NULL),(4,4,1,'[0],[1],','战略部','战略部','',NULL);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`pid`,`name`,`tips`,`num`) values (1,0,'状态',NULL,0),(2,1,'启用',NULL,1),(3,1,'禁用',NULL,2),(4,0,'性别',NULL,0),(5,4,'男',NULL,1),(6,4,'女',NULL,2),(7,0,'账号状态',NULL,0),(8,7,'启用',NULL,1),(9,7,'冻结',NULL,2),(10,7,'已删除',NULL,3);

/*Table structure for table `sys_expense` */

DROP TABLE IF EXISTS `sys_expense`;

CREATE TABLE `sys_expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(20,2) DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) DEFAULT NULL COMMENT '流程定义id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销表';

/*Data for the table `sys_expense` */

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否执行成功',
  `message` text COMMENT '具体消息',
  `ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录记录';

/*Data for the table `sys_login_log` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`code`,`pcode`,`pcodes`,`name`,`icon`,`url`,`num`,`levels`,`ismenu`,`tips`,`status`,`isopen`) values (1,'system','0','[0],','系统管理','fa-cog','#',0,1,1,NULL,1,1),(2,'mgr','system','[0],[system],','用户管理','fa-users','/mgr',1,2,1,NULL,1,0),(3,'mgr_list','mgr','[0],[system],[mgr],','用户列表',NULL,'/mgr/list',10,3,0,NULL,1,NULL),(4,'mgr_add','mgr','[0],[system],[mgr],','添加用户',NULL,'/mgr/add',1,3,0,NULL,1,0),(5,'mgr_edit','mgr','[0],[system],[mgr],','修改用户',NULL,'/mgr/edit',2,3,0,NULL,1,0),(6,'mgr_delete','mgr','[0],[system],[mgr],','删除用户',NULL,'/mgr/delete',3,3,0,NULL,1,0),(7,'mgr_reset','mgr','[0],[system],[mgr],','重置密码',NULL,'/mgr/reset',4,3,0,NULL,1,0),(8,'mgr_freeze','mgr','[0],[system],[mgr],','冻结用户',NULL,'/mgr/freeze',5,3,0,NULL,1,0),(9,'mgr_unfreeze','mgr','[0],[system],[mgr],','解除冻结用户',NULL,'/mgr/unfreeze',6,3,0,NULL,1,0),(10,'mgr_setRole','mgr','[0],[system],[mgr],','分配角色',NULL,'/mgr/setRole',7,3,0,NULL,1,0),(11,'to_user_edit','mgr','[0],[system],[mgr],','编辑用户跳转',NULL,'/mgr/user_edit',9,3,0,NULL,1,NULL),(12,'to_assign_role','mgr','[0],[system],[mgr],','分配角色跳转',NULL,'/mgr/role_assign',8,3,0,NULL,1,NULL),(13,'menu','system','[0],[system],','菜单管理','fa-th-list','/menu',2,2,1,NULL,1,0),(14,'menu_list','menu','[0],[system],[menu],','菜单列表',NULL,'/menu/list',5,3,0,NULL,1,NULL),(15,'menu_add','menu','[0],[system],[menu],','添加菜单',NULL,'/menu/add',1,3,0,NULL,1,0),(16,'menu_edit','menu','[0],[system],[menu],','修改菜单',NULL,'/menu/edit',2,3,0,NULL,1,0),(17,'menu_remove','menu','[0],[system],[menu],','删除菜单',NULL,'/menu/remove',3,3,0,NULL,1,0),(18,'to_menu_edit','menu','[0],[system],[menu],','菜单编辑跳转',NULL,'/menu/menu_edit',4,3,0,NULL,1,NULL),(19,'dept','system','[0],[system],','部门管理','fa-group','/dept',3,2,1,NULL,1,NULL),(20,'dept_list','dept','[0],[system],[dept],','部门列表',NULL,'/dept/list',5,3,0,NULL,1,NULL),(21,'dept_add','dept','[0],[system],[dept],','添加部门',NULL,'/dept/add',1,3,0,NULL,1,NULL),(22,'dept_update','dept','[0],[system],[dept],','修改部门',NULL,'/dept/update',1,3,0,NULL,1,NULL),(23,'dept_delete','dept','[0],[system],[dept],','删除部门',NULL,'/dept/delete',1,3,0,NULL,1,NULL),(24,'dept_detail','dept','[0],[system],[dept],','部门详情',NULL,'/dept/detail',6,3,0,NULL,1,NULL),(25,'to_dept_update','dept','[0],[system],[dept],','修改部门跳转',NULL,'/dept/dept_update',4,3,0,NULL,1,NULL),(26,'notice','system','[0],[system],','通知管理','fa-commenting-o','/notice',4,2,1,NULL,1,NULL),(27,'notice_add','notice','[0],[system],[notice],','添加通知',NULL,'/notice/add',1,3,0,NULL,1,NULL),(28,'notice_update','notice','[0],[system],[notice],','修改通知',NULL,'/notice/update',2,3,0,NULL,1,NULL),(29,'notice_delete','notice','[0],[system],[notice],','删除通知',NULL,'/notice/delete',3,3,0,NULL,1,NULL),(30,'hello','system','[0],[system],','公告','fa-rocket','/notice/hello',9,2,1,NULL,1,NULL),(31,'log','system','[0],[system],','业务日志','fa-newspaper-o','/log',6,2,1,NULL,1,0),(32,'log_list','log','[0],[system],[log],','日志列表',NULL,'/log/list',2,3,0,NULL,1,NULL),(33,'log_detail','log','[0],[system],[log],','日志详情',NULL,'/log/detail',3,3,0,NULL,1,NULL),(34,'log_clean','log','[0],[system],[log],','清空日志',NULL,'/log/delLog',3,3,0,NULL,1,NULL),(35,'loginLog','system','[0],[system],','登录日志','fa-newspaper-o','/loginLog',6,2,1,NULL,1,NULL),(36,'del_login_log','loginLog','[0],[system],[loginLog],','清空登录日志',NULL,'/loginLog/delLoginLog',1,3,0,NULL,1,NULL),(37,'login_log_list','loginLog','[0],[system],[loginLog],','登录日志列表',NULL,'/loginLog/list',2,3,0,NULL,1,NULL),(38,'role','system','[0],[system],','角色管理','fa-sitemap','/role',2,2,1,NULL,1,0),(39,'role_list','role','[0],[system],[role],','角色列表',NULL,'/role/list',7,3,0,NULL,1,NULL),(40,'role_add','role','[0],[system],[role],','添加角色',NULL,'/role/add',1,3,0,NULL,1,0),(41,'role_edit','role','[0],[system],[role],','修改角色',NULL,'/role/edit',2,3,0,NULL,1,0),(42,'role_remove','role','[0],[system],[role],','删除角色',NULL,'/role/remove',3,3,0,NULL,1,0),(43,'role_setAuthority','role','[0],[system],[role],','配置权限',NULL,'/role/setAuthority',4,3,0,NULL,1,0),(44,'to_role_edit','role','[0],[system],[role],','修改角色跳转',NULL,'/role/role_edit',5,3,0,NULL,1,NULL),(45,'to_role_assign','role','[0],[system],[role],','角色分配跳转',NULL,'/role/role_assign',6,3,0,NULL,1,NULL),(46,'dict','system','[0],[system],','字典管理','fa-book','/dict',4,2,1,NULL,1,NULL),(47,'dict_list','dict','[0],[system],[dict],','字典列表',NULL,'/dict/list',5,3,0,NULL,1,NULL),(48,'dict_add','dict','[0],[system],[dict],','添加字典',NULL,'/dict/add',1,3,0,NULL,1,NULL),(49,'dict_update','dict','[0],[system],[dict],','修改字典',NULL,'/dict/update',1,3,0,NULL,1,NULL),(50,'dict_delete','dict','[0],[system],[dict],','删除字典',NULL,'/dict/delete',1,3,0,NULL,1,NULL),(51,'dict_detail','dict','[0],[system],[dict],','字典详情',NULL,'/dict/detail',6,3,0,NULL,1,NULL),(52,'to_dict_edit','dict','[0],[system],[dict],','修改菜单跳转',NULL,'/dict/dict_edit',4,3,0,NULL,1,NULL),(53,'expense','system','[0],[system],','报销管理','fa-clone','#',5,2,1,NULL,1,NULL),(54,'expense_fill','expense','[0],[expense],','报销申请','fa-leaf','/expense',1,2,1,NULL,1,NULL),(55,'expense_progress','expense','[0],[expense],','报销审批','fa-leaf','/process',2,2,1,NULL,1,NULL),(56,'api_mgr','system','[0],[system],','接口文档','fa-leaf','/swagger-ui.html',8,2,1,NULL,1,NULL),(57,'code','system','[0],[system],','代码生成','fa-code','/code',6,2,1,NULL,1,NULL),(58,'druid','system','[0],[system],','监控管理','fa-database','/druid',7,2,1,NULL,1,NULL);

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`id`,`title`,`type`,`content`,`createtime`,`creater`) values (1,'世界',10,'欢迎使用Fit管理系统','2017-01-11 08:53:20',1),(2,'你好',NULL,'你好','2017-05-10 19:28:57',1);

/*Table structure for table `sys_operation_log` */

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) DEFAULT NULL COMMENT '类名称',
  `method` text COMMENT '方法名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否成功',
  `message` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

/*Data for the table `sys_operation_log` */

/*Table structure for table `sys_relation` */

DROP TABLE IF EXISTS `sys_relation`;

CREATE TABLE `sys_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_relation` */

insert  into `sys_relation`(`id`,`menuid`,`roleid`) values (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,1),(13,13,1),(14,14,1),(15,15,1),(16,16,1),(17,17,1),(18,18,1),(19,19,1),(20,20,1),(21,21,1),(22,22,1),(23,23,1),(24,24,1),(25,25,1),(26,26,1),(27,27,1),(28,28,1),(29,29,1),(30,30,1),(31,31,1),(32,32,1),(33,33,1),(34,34,1),(35,35,1),(36,36,1),(37,37,1),(38,38,1),(39,39,1),(40,40,1),(41,41,1),(42,42,1),(43,43,1),(44,44,1),(45,45,1),(46,46,1),(47,47,1),(48,48,1),(49,49,1),(50,50,1),(51,51,1),(52,52,1),(53,53,1),(54,54,1),(55,55,1),(56,56,1),(57,57,1),(58,58,1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`pid`,`name`,`deptid`,`tips`,`num`,`version`) values (1,0,'超级管理员',1,'administrator',1,1),(2,1,'测试员',2,'tester',2,NULL),(3,1,'运营员',3,'operator',2,NULL),(4,2,'临时',2,'temp',1,NULL),(5,1,'研发员',2,'programmer ',1,NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='管理员表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`avatar`,`account`,`password`,`salt`,`name`,`birthday`,`sex`,`email`,`phone`,`roleid`,`deptid`,`status`,`createtime`,`version`) values (1,NULL,'admin','be412b892087594f39bfd7dcaa2ddebe','8pgby','Admin','2017-05-05 00:00:00',2,'Admin@Admin.com','182123456','1',1,1,'2016-01-29 08:49:53',1),(2,NULL,'test','45abb7879f6a8268f1ef600e6038ac73','ssts3','test','2017-05-01 00:00:00',1,'abc@123.com','','2',2,3,'2017-05-16 20:33:37',NULL),(3,NULL,'boss','71887a5ad666a18f709e1d4e693d5a35','1f7bf','老板','2017-12-04 00:00:00',1,'','','1',1,1,'2017-12-04 22:24:02',NULL),(4,NULL,'manager','b53cac62e7175637d4beb3b16b2f7915','j3cs9','经理','2017-12-04 00:00:00',1,'','','1',1,1,'2017-12-04 22:24:24',NULL);

/* Procedure structure for procedure `test_insert` */

/*!50003 DROP PROCEDURE IF EXISTS  `test_insert` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `test_insert`()
BEGIN 
DECLARE a INT DEFAULT 1; 
WHILE (a <= 100) DO 
SET a = a + 1; 
insert into qm_order (id) values (a);
END WHILE; 
commit; 
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
