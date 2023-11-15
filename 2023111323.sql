/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.33 : Database - videosystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`videosystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `videosystem`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `Categoryid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`Categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

/*Data for the table `category` */

insert  into `category`(`Categoryid`,`name`) values (1,'综艺'),(2,'电影'),(3,'动漫'),(4,'纪录片');

/*Table structure for table `cl_user` */

DROP TABLE IF EXISTS `cl_user`;

CREATE TABLE `cl_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username_unique` (`user_name`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `cl_user` */

insert  into `cl_user`(`id`,`user_name`,`password`,`avatar`,`real_name`,`email`,`phone`,`type`,`create_time`,`update_time`,`deleted`) values (1,'123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(19,'18728766985','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(20,'13649561589','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(21,'13649876589','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(22,'18728694569','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `createTime` date NOT NULL,
  `videoId` int NOT NULL,
  `setId` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

/*Data for the table `comment` */

insert  into `comment`(`id`,`user_name`,`content`,`createTime`,`videoId`,`setId`) values (1,'123','123','2023-11-12',1,1),(2,'123','太好看了','2023-11-12',1,1);

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cover` varchar(500) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(500) NOT NULL,
  `catagoryid` int NOT NULL,
  `setid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `document` */

/*Table structure for table `t_file` */

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件存储路径',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
  `md5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件md5唯一标识串',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文件表';

/*Data for the table `t_file` */

insert  into `t_file`(`id`,`url`,`type`,`md5`,`createTime`) values (4,'M00/00/00/ZSqlBmVOQmGEGT_VAAAAANwgQUk860.rar','rar','232fsdg','2023-11-10 22:47:13'),(5,'M00/00/00/ZSqlBmVQgvaEXlrMAAAAANwgQUk844.rar','rar','fecd74984a13554cb7698bd9b22307c7','2023-11-12 15:47:05'),(6,'M00/00/00/ZSqlBmVRqgCEGu-LAAAAAJ5-4NM359.mp4','mp4','7fc685ccc7a9dd32d2841a0deede2858','2023-11-13 12:45:55'),(7,'M00/00/00/ZSqlBmVRr3SEZ28AAAAAAKReJhc412.png','png','88bdf6d857f06ab7d3f554d05d393988','2023-11-13 13:09:10'),(8,'M00/00/00/ZSqlBmVRsJeET0fqAAAAAJ4IIVM866.jpg','jpg','dfb1f438513d1d66a7ffd9e7f587eedd','2023-11-13 13:14:02'),(9,'M00/00/00/ZSqlBmVRsOGEEAkSAAAAAH06Qvw957.mp4','mp4','a1b65ff7e26025a60a504fc232b6e3da','2023-11-13 13:15:16'),(10,'M00/00/00/ZSqlBmVRseuEPc0LAAAAAH44TTQ154.jpg','jpg','2349bea8556eaa96bbe51239ee111042','2023-11-13 13:19:42'),(11,'M00/00/00/ZSqlBmVRsh-Ee8iuAAAAANqNlQI352.mp4','mp4','3abce06f97ffbaa5ef0cbbf1deccc5f9','2023-11-13 13:20:34'),(12,'M00/00/00/ZSqlBmVRx12Ee7ZVAAAAAGiJQ3k448.jpg','jpg','868e02b9651ffab5c9c75cf4e678e69b','2023-11-13 14:51:13'),(13,'M00/00/00/ZSqlBmVRx7eEaFLvAAAAAA5Kvu4722.mp4','mp4','4fc69f906168d638c6425ef675b8c466','2023-11-13 14:52:42');

/*Table structure for table `variety` */

DROP TABLE IF EXISTS `variety`;

CREATE TABLE `variety` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cover` varchar(500) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(500) NOT NULL,
  `categoryid` int NOT NULL,
  `setid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `variety` */

/*Table structure for table `videolist` */

DROP TABLE IF EXISTS `videolist`;

CREATE TABLE `videolist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cover` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(1000) NOT NULL,
  `setid` int NOT NULL,
  `tags` varchar(100) NOT NULL,
  `videoSrc` varchar(500) NOT NULL,
  `resolution` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

/*Data for the table `videolist` */

insert  into `videolist`(`id`,`cover`,`title`,`describe`,`setid`,`tags`,`videoSrc`,`resolution`) values (1,'http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRr3SEZ28AAAAAAKReJhc412.png','悲惨oc人','配乐音效来自网络，仅作为学习素材，侵删。角色台词是自己配的，能力有限可能不尽人意请多包涵',1,'动画','http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRqgCEGu-LAAAAAJ5-4NM359.mp4',720),(2,'http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRsJeET0fqAAAAAJ4IIVM866.jpg','procreate动画','是用procreat画的短片动画，关于菊花，少女在种菊花时的幻想。偏意识流，其实没有想发出来的，今天一看b站我居然有50个粉丝，有点感动，想要不也发点东西吧。第一次发有点紧张希望有人会喜欢。',2,'动画','http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRsOGEEAkSAAAAAH06Qvw957.mp4',720),(3,'http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRseuEPc0LAAAAAH44TTQ154.jpg','30s动画','是大一开学第一次动画作业',3,'动画','http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRsh-Ee8iuAAAAANqNlQI352.mp4',720),(4,'http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRx12Ee7ZVAAAAAGiJQ3k448.jpg','原画设计的动画','大一下动画作业合集',4,'动画','http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVRx7eEaFLvAAAAAA5Kvu4722.mp4',720);

/*Table structure for table `videoset` */

DROP TABLE IF EXISTS `videoset`;

CREATE TABLE `videoset` (
  `setid` int NOT NULL AUTO_INCREMENT,
  `setName` varchar(100) NOT NULL,
  PRIMARY KEY (`setid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `videoset` */

insert  into `videoset`(`setid`,`setName`) values (1,'test1'),(2,'test2'),(3,'test3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
