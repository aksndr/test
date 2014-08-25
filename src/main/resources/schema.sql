DROP TABLE IF EXISTS `test`.`counters`;
CREATE TABLE `test`.`counters` (
  `sn`     INT(10) UNSIGNED NOT NULL,
  `descr`  VARCHAR(100)     NOT NULL,
  `typeId` INT(11) DEFAULT NULL,
  `flatId` INT(11) DEFAULT NULL,
  PRIMARY KEY (`sn`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `test`.`countertypes`;
CREATE TABLE `test`.`countertypes` (
  `id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(45)      NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `test`.`flats`;
CREATE TABLE `test`.`flats` (
  `id`      INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `houseId` INT(10) UNSIGNED DEFAULT NULL,
  `flatNum` VARCHAR(45)      NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `Index_2` (`flatNum`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `test`.`houses`;
CREATE TABLE `test`.`houses` (
  `id`      INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(1024)    NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `test`.`users`;
CREATE TABLE `test`.`users` (
  `id`        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login`     VARCHAR(45)      NOT NULL,
  `firstName` VARCHAR(45) DEFAULT NULL,
  `lastName`  VARCHAR(45) DEFAULT NULL,
  `flatId`    VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_2` (`login`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;