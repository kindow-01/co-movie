/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - videosystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`videosystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `videosystem`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `Categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`Categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`Categoryid`,`name`) values (1,'综艺'),(2,'电影'),(3,'动漫'),(4,'纪录片');

/*Table structure for table `cl_user` */

DROP TABLE IF EXISTS `cl_user`;

CREATE TABLE `cl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username_unique` (`user_name`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `cl_user` */

insert  into `cl_user`(`id`,`user_name`,`password`,`avatar`,`real_name`,`email`,`phone`,`type`,`create_time`,`update_time`,`deleted`) values (1,'123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(19,'18728766985','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(20,'13649561589','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(21,'13649876589','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(22,'18728694569','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

/*Table structure for table `comic` */

DROP TABLE IF EXISTS `comic`;

CREATE TABLE `comic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(1000) NOT NULL,
  `setid` int(11) NOT NULL,
  `tags` varchar(100) NOT NULL,
  `videoSrc` varchar(500) NOT NULL,
  `resolution` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `comic` */

insert  into `comic`(`id`,`cover`,`title`,`describe`,`setid`,`tags`,`videoSrc`,`resolution`) values (1,'https://img1.wxzxzj.com/vpc-example-cover-your-name-c.png','你的名字','《你的名字。》是新海诚执导，神木隆之介、上白石萌音担任主要配音，Comix Wave Films制作的一部原创日本动画电影，于2016年8月26日在日本上映。中国大陆于2016年12月2日上映。影片讲述了男女高中生在梦中相遇，并寻找彼此的故事。中国大陆于2017年8月26日全网首播。',1,'剧情,爱情','https://media.vued.vanthink.cn/sparkle_your_name_am720p.mp4',720);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `createTime` date NOT NULL,
  `videoId` int(11) NOT NULL,
  `setId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`user_name`,`content`,`createTime`,`videoId`,`setId`) values (1,'123','123','2023-11-12',1,1),(2,'123','太好看了','2023-11-12',1,1);

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover` varchar(500) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(500) NOT NULL,
  `catagoryid` int(11) NOT NULL,
  `setid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `document` */

/*Table structure for table `variety` */

DROP TABLE IF EXISTS `variety`;

CREATE TABLE `variety` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover` varchar(500) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(500) NOT NULL,
  `categoryid` int(11) NOT NULL,
  `setid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `variety` */

/*Table structure for table `videolist` */

DROP TABLE IF EXISTS `videolist`;

CREATE TABLE `videolist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover` varchar(500) NOT NULL,
  `title` varchar(100) NOT NULL,
  `describe` varchar(500) NOT NULL,
  `setid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `videolist` */

insert  into `videolist`(`id`,`cover`,`title`,`describe`,`setid`) values (1,'https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001','听说你喜欢我','彭冠英王楚然先婚后爱',1),(2,'https://puui.qpic.cn/vcover_hz_pic/0/mzc002007knmh3g1673530815496/332?max_age=7776001','三体','中国科幻巅峰IP',2),(3,'https://puui.qpic.cn/vcover_hz_pic/0/mzc002006sce2l41669280941978/332?max_age=7776001','迷寻','一场尘封十年的拐卖恩怨',1),(4,'https://puui.qpic.cn/vcover_hz_pic/0/wf6dzcsfe81z03i1669879665110/332?max_age=7776001','原始求生记 第3季','硬核！零装备挑战野外生存',3);

/*Table structure for table `videoset` */

DROP TABLE IF EXISTS `videoset`;

CREATE TABLE `videoset` (
  `setid` int(11) NOT NULL AUTO_INCREMENT,
  `setName` varchar(100) NOT NULL,
  PRIMARY KEY (`setid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `videoset` */

insert  into `videoset`(`setid`,`setName`) values (1,'test1'),(2,'test2'),(3,'test3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
