CREATE TABLE `counters`.`counters` (
  `sn`     INT(10) UNSIGNED NOT NULL,
  `descr`  VARCHAR(100)     NOT NULL,
  `typeid` INT(11) DEFAULT NULL,
  `flatid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`sn`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

CREATE TABLE `counters`.`countertypes` (
  `id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `typename` VARCHAR(45)      NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =1
  DEFAULT CHARSET =utf8;

CREATE TABLE `counters`.`flats` (
  `id`      INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `houseid` INT(10) UNSIGNED DEFAULT NULL,
  `flatnum` VARCHAR(45)      NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `Index_2` (`flatnum`) USING BTREE
)
  ENGINE =InnoDB
  AUTO_INCREMENT =1
  DEFAULT CHARSET =utf8;

CREATE TABLE `counters`.`houses` (
  `id`      INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(1024)    NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =1
  DEFAULT CHARSET =utf8;

CREATE TABLE `counters`.`users` (
  `id`        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login`     VARCHAR(45)      NOT NULL,
  `firstname` VARCHAR(45) DEFAULT NULL,
  `lastname`  VARCHAR(45) DEFAULT NULL,
  `flatid`    VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `Index_2` (`login`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =1
  DEFAULT CHARSET =utf8;

  DROP TABLE IF EXISTS `counters`.`records`;
  CREATE TABLE  `counters`.`records` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `value` decimal(10,0) NOT NULL,
    `datetime` datetime NOT NULL,
    `counterid` int(10) unsigned NOT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;