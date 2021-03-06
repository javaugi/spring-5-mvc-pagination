/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
/**
 * Author:  david
 * Created: Feb 22, 2019
 */

create database spring5mvnpaging;
grant all on spring5mvnpaging.* to root@localhost identified by 'mysql';


CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `price` decimal(10,1) NOT NULL,
  `quantity` int(11) NOT NULL,
  `description` text NOT NULL,
  `status` tinyint(1) default 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Mobile 1', '1.0', 5, 'description 2', 1);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Mobile 2', '2.0', 2, 'description 1', 1);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Mobile 3', '11.0', 9, 'description 3', 1);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Laptop 4', '2.0', 15, 'description 9', 1);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Computer 1', '16.0', 8, 'description 7', 0);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Laptop 1', '22.0', 8, 'description 7', 0);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Laptop 2', '4.0', 11, 'description 8', 0);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Laptop 3', '9.0', 15, 'description 9', 1);
INSERT INTO `product` (`name`, `price`, `quantity`, `description`, `status`) VALUES('Computer 2', '3.0', 8, 'description 7', 0);

create database spring_social;
grant all on spring_social.* to root@localhost identified by 'mysql';
use spring_social;
CREATE TABLE `users` (
  `id` long NOT NULL,
  `name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `imageUrl` varchar(128) NOT NULL,
  `providerId` varchar(128) NOT NULL,
  `emailVerified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

