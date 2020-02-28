-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- Server version:               10.3.15-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for mybatis
CREATE DATABASE IF NOT EXISTS `mybatis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mybatis`;

-- Dumping structure for table mybatis.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table mybatis.person: ~2 rows (approximately)
DELETE FROM `person`;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `name`) VALUES
	(1, 'gfdg'),
	(2, '457');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

-- Dumping structure for table mybatis.role
CREATE TABLE IF NOT EXISTS `role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table mybatis.role: ~2 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`rid`, `role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table mybatis.sclass
CREATE TABLE IF NOT EXISTS `sclass` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table mybatis.sclass: ~6 rows (approximately)
DELETE FROM `sclass`;
/*!40000 ALTER TABLE `sclass` DISABLE KEYS */;
INSERT INTO `sclass` (`sid`, `name`) VALUES
	(2, '1班'),
	(3, '2班'),
	(4, '3班'),
	(5, '4班'),
	(6, '5班'),
	(8, '6班');
/*!40000 ALTER TABLE `sclass` ENABLE KEYS */;

-- Dumping structure for table mybatis.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- Dumping data for table mybatis.student: ~11 rows (approximately)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `cid`, `name`, `age`) VALUES
	(1, 1, '小明', 12),
	(2, 1, '小王', 2),
	(4, 2, '风', 1),
	(5, 2, '官方', 2),
	(7, 2, '反对', 22),
	(9, 3, '用户', 13),
	(10, 4, '官方', 14),
	(12, 4, '第三方', 44),
	(23, 5, '得十', 11),
	(25, 5, '大额', 11);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table mybatis.user
CREATE TABLE IF NOT EXISTS `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `clazz` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Dumping data for table mybatis.user: ~5 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`uid`, `name`, `password`, `clazz`) VALUES
	(1, 'admin', '$2a$10$SkecSrHTOFnyhi03l7JacO2mZ.aYBzLFPiSnve8bNjH9eb.76ieZu', -2),
	(20, 'user', '$2a$10$6iK/72WGq9wqhh3OMNwJJ.RC5SsEouZ0H/SxIq1JeW1wxT/uOCSp6', 2),
	(22, 'test', '$2a$10$V31ZBm6behDNnQr7P3XLw.sDsokkmLA8yZI8ej7BN5Xjxs6uYuwwO', 2),
	(34, 'dfsf', '$2a$10$favasgE5bTkW61QKlhQ8T.TYs3U/YzpZhoECYd2CpqZNM2CR0sUNG', 3),
	(36, 'hjg', '$2a$10$zeEuPe0oz0LoGF7R9wDVa.JyyesTl9AjwUQGjzHhLyB5ts4Icccoq', 4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table mybatis.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `FK_user_role_role_key` FOREIGN KEY (`role_id`) REFERENCES `role` (`rid`),
  CONSTRAINT `FK_user_role_user_key` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mybatis.user_role: ~5 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(1, 2),
	(20, 2),
	(34, 1),
	(34, 2),
	(36, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
