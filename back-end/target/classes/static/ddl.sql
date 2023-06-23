CREATE DATABASE IF NOT EXISTS Film_system;
use Film_system;
DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
`id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`theater_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`start_time` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`ticket` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`price` decimal(10,2) UNSIGNED NULL,
`picture` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`count` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`create_time` bigint(11) UNSIGNED NULL,
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `order_bill`;
CREATE TABLE `order_bill` (
`id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`film_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`start_t_ime` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`number` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`total_price` decimal(10,2) UNSIGNED NULL,
`create_time` bigint(11) UNSIGNED NULL,
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`account` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`password` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`nickname` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`gender` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`mobile` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`email` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`identity` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`create_time` bigint(11) UNSIGNED NULL,
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


create user 'Film_system'@'%' identified by '123456';
grant all privileges on Film_system.* to 'Film_system'@'%';
flush privileges;