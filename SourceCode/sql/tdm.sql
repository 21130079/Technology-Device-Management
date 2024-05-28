/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : tdm

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 28/05/2024 21:59:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `passwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for devices
-- ----------------------------
DROP TABLE IF EXISTS `devices`;
CREATE TABLE `devices`  (
  `idDevice` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nameDevice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` float NOT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manufacturingDate` date NOT NULL,
  `weight` float NOT NULL,
  `urlImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quantityInStock` int NOT NULL,
  PRIMARY KEY (`idDevice`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of devices
-- ----------------------------
INSERT INTO `devices` VALUES ('ace017', 'Acer Predator Helios 300', 'Laptop', 1399.99, 'Acer', '2024-03-15', 2.5, '/img/devices/acerpredatorhelios300.jpg', 150);
INSERT INTO `devices` VALUES ('ama009', 'Amazon Fire HD 10', 'Tablet', 199.99, 'Amazon', '2024-03-15', 0.504, '/img/devices/amazonfirehd10.jpg', 125);
INSERT INTO `devices` VALUES ('app008', 'Apple Watch Series 8', 'Smartwatch', 399, 'Apple', '2024-04-10', 0.07, '/img/devices/applewatchseries8.jpg', 250);
INSERT INTO `devices` VALUES ('asu022', 'ASUS ROG Zephyrus G14', 'Laptop', 1799.99, 'ASUS', '2024-02-10', 1.6, '/img/devices/asusrogzephyrusg14.jpg', 140);
INSERT INTO `devices` VALUES ('bea025', 'Beats Studio Buds', 'Headphones', 149.99, 'Beats', '2024-03-15', 0.005, '/img/devices/beatsstudiobuds.jpg', 220);
INSERT INTO `devices` VALUES ('bos005', 'Bose Noise Cancelling Headphones 700', 'Headphones', 379.99, 'Bose', '2024-03-20', 0.25, '/img/devices/bosenoise700.jpg', 180);
INSERT INTO `devices` VALUES ('del007', 'Dell XPS 15', 'Laptop', 1499.99, 'Dell', '2024-01-05', 1.8, '/img/devices/dellxps15.jpg', 165);
INSERT INTO `devices` VALUES ('fos018', 'Fossil Gen 6 Smartwatch', 'Smartwatch', 299.95, 'Fossil', '2024-02-20', 0.08, '/img/devices/fossilgen6.jpg', 230);
INSERT INTO `devices` VALUES ('gar003', 'Garmin Fenix 7', 'Smartwatch', 799.99, 'Garmin', '2024-01-10', 0.082, '/img/devices/garminfenix7.jpg', 100);
INSERT INTO `devices` VALUES ('gar023', 'Garmin Venu 2 Plus', 'Smartwatch', 399.99, 'Garmin', '2024-01-05', 0.082, '/img/devices/garminvenu2plus.jpg', 240);
INSERT INTO `devices` VALUES ('goo006', 'Google Pixel 7 Pro', 'Smartphone', 899, 'Google', '2024-02-10', 0.21, '/img/devices/googlepixel7pro.jpg', 220);
INSERT INTO `devices` VALUES ('hp 012', 'HP Spectre x360', 'Laptop', 1299.99, 'HP', '2024-04-05', 1.3, '/img/devices/hpspectrex360.jpg', 150);
INSERT INTO `devices` VALUES ('hua013', 'Huawei Watch GT 3', 'Smartwatch', 299.99, 'Huawei', '2024-03-20', 0.056, '/img/devices/huaweiwatchgt3.jpg', 220);
INSERT INTO `devices` VALUES ('jbl010', 'JBL Flip 6 Portable Bluetooth Speaker', 'Audio', 129.95, 'JBL', '2024-02-20', 0.54, '/img/devices/jblflip6.jpg', 200);
INSERT INTO `devices` VALUES ('len014', 'Lenovo Tab P11 Plus', 'Tablet', 299.99, 'Lenovo', '2024-02-10', 0.5, '/img/devices/lenovotabp11plus.jpg', 190);
INSERT INTO `devices` VALUES ('mac002', 'MacBook Pro 14-inch M1 Pro', 'Laptop', 1999, 'Apple', '2024-02-20', 1.4, '/img/devices/macbookpro14m1pro.jpg', 150);
INSERT INTO `devices` VALUES ('mic004', 'Microsoft Surface Pro 8', 'Tablet', 1099.99, 'Microsoft', '2024-04-05', 0.77, '/img/devices/surfacepro8.jpg', 120);
INSERT INTO `devices` VALUES ('mic024', 'Microsoft Surface Go 3', 'Tablet', 399.99, 'Microsoft', '2024-04-10', 0.547, '/img/devices/surfacego3.jpg', 180);
INSERT INTO `devices` VALUES ('mot021', 'Motorola Edge 40', 'Smartphone', 699, 'Motorola', '2024-03-20', 0.21, '/img/devices/motorolaedge40.jpg', 210);
INSERT INTO `devices` VALUES ('one011', 'OnePlus 10 Pro', 'Smartphone', 899, 'OnePlus', '2024-01-10', 0.21, '/img/devices/oneplus10pro.jpg', 180);
INSERT INTO `devices` VALUES ('sam001', 'Samsung Galaxy S22 Ultra', 'Smartphone', 1199.99, 'Samsung', '2024-03-15', 0.23, '/img/devices/samsunggalaxys22ultra.jpg', 200);
INSERT INTO `devices` VALUES ('sam019', 'Samsung Galaxy Tab S8', 'Tablet', 699.99, 'Samsung', '2024-01-10', 0.58, '/img/devices/samsunggalaxytabs8.jpg', 140);
INSERT INTO `devices` VALUES ('sen020', 'Sennheiser Momentum 4 Wireless Headphones', 'Headphones', 399.95, 'Sennheiser', '2024-04-05', 0.3, '/img/devices/sennheisermomentum4.jpg', 190);
INSERT INTO `devices` VALUES ('son015', 'Sony WH-XB910N Extra Bass Wireless Headphones', 'Headphones', 249.99, 'Sony', '2024-01-05', 0.25, '/img/devices/sonywhxb910n.jpg', 210);
INSERT INTO `devices` VALUES ('xia016', 'Xiaomi Mi 12', 'Smartphone', 799, 'Xiaomi', '2024-04-10', 0.21, '/img/devices/xiaomimi12.jpg', 200);

-- ----------------------------
-- Table structure for orderdevices
-- ----------------------------
DROP TABLE IF EXISTS `orderdevices`;
CREATE TABLE `orderdevices`  (
  `idOrderDevice` int NOT NULL AUTO_INCREMENT,
  `idDevice` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idOrder` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idOrderDevice`) USING BTREE,
  INDEX `idDevice`(`idDevice` ASC) USING BTREE,
  INDEX `idOrder`(`idOrder` ASC) USING BTREE,
  CONSTRAINT `orderdevices_ibfk_1` FOREIGN KEY (`idDevice`) REFERENCES `devices` (`idDevice`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderdevices_ibfk_2` FOREIGN KEY (`idOrder`) REFERENCES `orders` (`idOrder`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orderdevices
-- ----------------------------
INSERT INTO `orderdevices` VALUES (71, 'ace017', 'c38726e7-225d-4d8e-acb3-9e3dd78fb2c8');
INSERT INTO `orderdevices` VALUES (72, 'ama009', 'c38726e7-225d-4d8e-acb3-9e3dd78fb2c8');
INSERT INTO `orderdevices` VALUES (73, 'ace017', 'e324c766-87e4-4f29-aee3-9a769f805274');
INSERT INTO `orderdevices` VALUES (74, 'ace017', '2af983ea-91f0-48e7-bca8-ecf580344bef');
INSERT INTO `orderdevices` VALUES (75, 'ama009', '2af983ea-91f0-48e7-bca8-ecf580344bef');
INSERT INTO `orderdevices` VALUES (76, 'ace017', 'b209bebc-b568-453c-aff8-49ffdfd10efa');
INSERT INTO `orderdevices` VALUES (77, 'ace017', '87ed9618-1f73-47ed-b8fa-b01cfaa7b9c4');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `idOrder` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `invoiceDate` date NOT NULL,
  PRIMARY KEY (`idOrder`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2af983ea-91f0-48e7-bca8-ecf580344bef', '2024-05-28');
INSERT INTO `orders` VALUES ('87ed9618-1f73-47ed-b8fa-b01cfaa7b9c4', '2024-05-28');
INSERT INTO `orders` VALUES ('b209bebc-b568-453c-aff8-49ffdfd10efa', '2024-05-28');
INSERT INTO `orders` VALUES ('c38726e7-225d-4d8e-acb3-9e3dd78fb2c8', '2024-05-28');
INSERT INTO `orders` VALUES ('e324c766-87e4-4f29-aee3-9a769f805274', '2024-05-28');

-- ----------------------------
-- Table structure for roles_account
-- ----------------------------
DROP TABLE IF EXISTS `roles_account`;
CREATE TABLE `roles_account`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`, `role`) USING BTREE,
  CONSTRAINT `roles_account_ibfk_1` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles_account
-- ----------------------------
INSERT INTO `roles_account` VALUES ('admin', 'admin');

-- ----------------------------
-- Triggers structure for table devices
-- ----------------------------
DROP TRIGGER IF EXISTS `generate_idDevice_trigger`;
delimiter ;;
CREATE TRIGGER `generate_idDevice_trigger` BEFORE INSERT ON `devices` FOR EACH ROW BEGIN
    DECLARE new_id INT;
    SET new_id = (SELECT IFNULL(MAX(CAST(SUBSTRING(idDevice, 4) AS UNSIGNED)), 0) + 1 FROM devices);
    SET NEW.idDevice = CONCAT(LOWER(SUBSTRING(NEW.nameDevice, 1, 3)), LPAD(new_id, 3, '0'));
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
