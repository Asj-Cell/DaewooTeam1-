-- 1. 데이터베이스 먼저 생성
CREATE
DATABASE booking_db
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

-- 2. 사용자 생성
CREATE
USER 'user'@'localhost' IDENTIFIED BY '1234';

-- 3. 생성된 데이터베이스에 대한 권한 부여
GRANT ALL PRIVILEGES ON booking_db.* TO
'user'@'localhost';

-- 4. 권한 적용
FLUSH
PRIVILEGES;
-- 데이터베이스가 없다면 생성
CREATE DATABASE IF NOT EXISTS `booking_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `booking_db`;

-- 테이블 삭제 (참조 역순으로 진행)
DROP TABLE IF EXISTS `pay`;
DROP TABLE IF EXISTS `payment`;
DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `favorites`;
DROP TABLE IF EXISTS `package_image`;
DROP TABLE IF EXISTS `travel_package`;
DROP TABLE IF EXISTS `hotel_image`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `amenities`;
DROP TABLE IF EXISTS `freebies`;
DROP TABLE IF EXISTS `hotel`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `user`;


--
-- Table structure for table `user`
--
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `address` varchar(100) DEFAULT NULL,
                        `background_image_url` varchar(255) DEFAULT NULL,
                        `birth_date` date DEFAULT NULL,
                        `email` varchar(100) NOT NULL,
                        `image_url` varchar(255) DEFAULT NULL,
                        `password` varchar(100) NOT NULL,
                        `phone_number` varchar(100) NOT NULL,
                        `user_name` varchar(100) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'서울시 강남구','http://example.com/images/minjun_bg.jpg','1990-01-15','minjun.kim@example.com','http://example.com/images/minjun.jpg','password123','010-1234-5678','김민준'),(2,'부산시 해운대구','http://example.com/images/seoyeon_bg.jpg','1992-05-20','seoyeon.lee@example.com','http://example.com/images/seoyeon.jpg','password456','010-2345-6789','이서연'),(3,'인천시 연수구','http://example.com/images/junho_bg.jpg','1988-08-10','junho.park@example.com','http://example.com/images/junho.jpg','password789','010-3456-7890','박준호'),(4,'대구시 수성구','http://example.com/images/jiwoo_bg.jpg','1995-11-25','jiwoo.choi@example.com','http://example.com/images/jiwoo.jpg','password101','010-4567-8901','최지우'),(5,'광주시 서구','http://example.com/images/yujin_bg.jpg','1998-03-30','yujin.jung@example.com','http://example.com/images/yujin.jpg','password202','010-5678-9012','정유진');
UNLOCK TABLES;


--
-- Table structure for table `city`
--
CREATE TABLE `city` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `city_name` varchar(20) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `city`
--
LOCK TABLES `city` WRITE;
INSERT INTO `city` VALUES (1,'서울'),(2,'부산'),(3,'제주'),(4,'인천'),(5,'경주');
UNLOCK TABLES;


--
-- Table structure for table `hotel`
--
CREATE TABLE `hotel` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `address` varchar(100) NOT NULL,
                         `checkin_time` time(6) NOT NULL,
                         `checkout_time` time(6) NOT NULL,
                         `grade` int(11) NOT NULL,
                         `latitude` double NOT NULL,
                         `longitude` double NOT NULL,
                         `name` varchar(200) NOT NULL,
                         `overview` varchar(100) NOT NULL,
                         `city_id` bigint(20) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK_hotel_to_city` (`city_id`),
                         CONSTRAINT `FK_hotel_to_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hotel`
--
LOCK TABLES `hotel` WRITE;
INSERT INTO `hotel` VALUES (1,'서울시 중구 동호로 249','15:00:00.000000','12:00:00.000000',5,37.5558,127.0053,'신라호텔','최고급 호텔',1),(2,'부산시 해운대구 해운대해변로 296','15:00:00.000000','11:00:00.000000',5,35.1598,129.1603,'파라다이스 호텔 부산','해운대 해변에 위치한 럭셔리 호텔',2),(3,'제주특별자치도 서귀포시 중문관광로72번길 35','14:00:00.000000','11:00:00.000000',5,33.2476,126.4107,'롯데호텔 제주','중문관광단지에 위치한 리조트형 호텔',3),(4,'인천시 연수구 테크노파크로 200','15:00:00.000000','11:00:00.000000',5,37.3947,126.6384,'경원재 앰배서더 인천','한옥 호텔',4),(5,'경북 경주시 보문로 484-7','15:00:00.000000','11:00:00.000000',5,35.84,129.2818,'힐튼 경주','보문호수에 위치한 호텔',5);
UNLOCK TABLES;

--
-- Table structure for table `amenities`
--
CREATE TABLE `amenities` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `airconditioning` bit(1) DEFAULT NULL,
                             `bar_lounge` bit(1) DEFAULT NULL,
                             `fitness_center` bit(1) DEFAULT NULL,
                             `front_desk24` bit(1) DEFAULT NULL,
                             `indoor_pool` bit(1) DEFAULT NULL,
                             `outdoor_pool` bit(1) DEFAULT NULL,
                             `restaurant` bit(1) DEFAULT NULL,
                             `roomservice` bit(1) DEFAULT NULL,
                             `spa_wellness_center` bit(1) DEFAULT NULL,
                             `tea_coffee_machine` bit(1) DEFAULT NULL,
                             `hotel_id` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UK_amenities_hotel_id` (`hotel_id`),
                             CONSTRAINT `FK_amenities_to_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `amenities`
--
LOCK TABLES `amenities` WRITE;
INSERT INTO `amenities` VALUES (1,1,1,1,1,1,1,1,1,1,1,1),(2,1,1,1,1,0,1,1,1,1,1,2),(3,1,1,1,1,1,1,1,1,1,1,3),(4,1,0,0,1,0,0,1,1,0,1,4),(5,1,1,1,1,1,1,1,1,1,1,5);
UNLOCK TABLES;


--
-- Table structure for table `freebies`
--
CREATE TABLE `freebies` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `airport_shuttlebus` bit(1) DEFAULT NULL,
                            `breakfast_included` bit(1) DEFAULT NULL,
                            `free_cancellation` bit(1) DEFAULT NULL,
                            `free_parking` bit(1) DEFAULT NULL,
                            `free_wifi` bit(1) DEFAULT NULL,
                            `hotel_id` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_freebies_hotel_id` (`hotel_id`),
                            CONSTRAINT `FK_freebies_to_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `freebies`
--
LOCK TABLES `freebies` WRITE;
INSERT INTO `freebies` VALUES (1,1,1,1,1,1,1),(2,0,0,1,1,1,2),(3,1,1,0,1,1,3),(4,0,1,1,1,1,4),(5,0,0,1,1,1,5);
UNLOCK TABLES;


--
-- Table structure for table `room`
--
CREATE TABLE `room` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `bed` varchar(50) DEFAULT NULL,
                        `max_guests` int(11) NOT NULL,
                        `name` varchar(20) NOT NULL,
                        `price` decimal(38,2) NOT NULL,
                        `room_number` varchar(10) NOT NULL,
                        `view` varchar(20) DEFAULT NULL,
                        `hotel_id` bigint(20) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK_room_to_hotel` (`hotel_id`),
                        CONSTRAINT `FK_room_to_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `room`
--
LOCK TABLES `room` WRITE;
INSERT INTO `room` VALUES (6,'더블',2,'디럭스 룸',300000.00,'101','시티 뷰',1),(7,'킹',3,'스위트 룸',450000.00,'202','오션 뷰',2),(8,'트윈',4,'패밀리 룸',250000.00,'303','가든 뷰',3),(9,'퀸',2,'이그제큐티브 룸',350000.00,'404','파크 뷰',4),(10,'싱글',1,'스탠다드 룸',200000.00,'505','마운틴 뷰',5);
UNLOCK TABLES;


--
-- Table structure for table `hotel_image`
--
CREATE TABLE `hotel_image` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `image_url` varchar(255) NOT NULL,
                               `sequence` int(11) NOT NULL,
                               `size` int(11) NOT NULL,
                               `hotel_id` bigint(20) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK_hotel_image_to_hotel` (`hotel_id`),
                               CONSTRAINT `FK_hotel_image_to_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hotel_image`
--
LOCK TABLES `hotel_image` WRITE;
INSERT INTO `hotel_image` VALUES (1,'http://example.com/images/shilla_hotel_1.jpg',1,1024,1),(2,'http://example.com/images/paradise_busan_1.jpg',1,2048,2),(3,'http://example.com/images/lotte_jeju_1.jpg',1,1536,3),(4,'http://example.com/images/gyeongwonjae_incheon_1.jpg',1,1280,4),(5,'http://example.com/images/hilton_gyeongju_1.jpg',1,1800,5);
UNLOCK TABLES;


--
-- Table structure for table `travel_package`
--
CREATE TABLE `travel_package` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `description` varchar(300) NOT NULL,
                                  `end_date` date NOT NULL,
                                  `price` decimal(38,2) NOT NULL,
                                  `st_date` date NOT NULL,
                                  `title` varchar(50) NOT NULL,
                                  `city_id` bigint(20) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK_travel_package_to_city` (`city_id`),
                                  CONSTRAINT `FK_travel_package_to_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `travel_package`
--
LOCK TABLES `travel_package` WRITE;
INSERT INTO `travel_package` VALUES (1,'서울의 주요 관광지를 둘러보는 패키지','2024-06-12',350000.00,'2024-06-10','서울 2박 3일 시티 투어',1),(2,'부산의 맛집을 탐방하는 미식 여행 패키지','2024-07-18',450000.00,'2024-07-15','부산 3박 4일 미식 여행',2),(3,'제주의 자연 속에서 힐링하는 여행','2024-08-24',550000.00,'2024-08-20','제주 4박 5일 힐링 여행',3),(4,'인천 차이나타운과 개항장 거리를 둘러보는 패키지','2024-09-06',150000.00,'2024-09-05','인천 1박 2일 차이나타운 투어',4),(5,'신라의 역사를 따라 떠나는 여행','2024-10-12',250000.00,'2024-10-10','경주 2박 3일 역사 여행',5),(6,'시간이 멈춘 도시, 말라카에서 과거로의 여행을 떠나보세요. 유네스코 세계문화유산을 거닐며 역사와 낭만을 동시에 느낄 수 있습니다.','2025-10-05',700.00,'2025-10-01','말라카 투어',5),(7,'활기찬 도시 멜버른의 매력에 빠져보세요.','2025-11-15',130000.00,'2025-11-10','Melbourne Journey',4),(8,'예술과 낭만의 도시 파리를 탐험하는 시간.','2025-12-06',150000.00,'2025-12-01','파리 Adventure',2),(9,'전통과 현대가 공존하는 런던의 모든 것.','2025-12-25',130000.00,'2025-12-20','런던 Classic',3);
UNLOCK TABLES;


--
-- Table structure for table `package_image`
--
CREATE TABLE `package_image` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `image_url` varchar(255) NOT NULL,
                                 `package_id` bigint(20) NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FK_package_image_to_package` (`package_id`),
                                 CONSTRAINT `FK_package_image_to_package` FOREIGN KEY (`package_id`) REFERENCES `travel_package` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `package_image`
--
LOCK TABLES `package_image` WRITE;
INSERT INTO `package_image` VALUES (1,'http://example.com/images/seoul_tour.jpg',1),(2,'http://example.com/images/busan_food.jpg',2),(3,'http://example.com/images/jeju_healing.jpg',3),(4,'http://example.com/images/incheon_chinatown.jpg',4),(5,'http://example.com/images/gyeongju_history.jpg',5),(6,'https://example.com/images/malacca_tour_1.jpg',1),(7,'https://example.com/images/malacca_tour_2.jpg',1),(8,'https://example.com/images/malacca_tour_3.jpg',1),(9,'https://example.com/images/melbourne_journey.jpg',2),(10,'https://example.com/images/paris_adventure.jpg',3),(11,'https://example.com/images/london_classic.jpg',4);
UNLOCK TABLES;


--
-- Table structure for table `favorites`
--
CREATE TABLE `favorites` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `hotel_id` bigint(20) DEFAULT NULL,
                             `user_id` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FK_favorites_to_hotel` (`hotel_id`),
                             KEY `FK_favorites_to_user` (`user_id`),
                             CONSTRAINT `FK_favorites_to_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
                             CONSTRAINT `FK_favorites_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `favorites`
--
LOCK TABLES `favorites` WRITE;
UNLOCK TABLES;


--
-- Table structure for table `review`
--
CREATE TABLE `review` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `content` varchar(255) NOT NULL,
                          `report_yn` bit(1) NOT NULL,
                          `user_rating_score` double NOT NULL,
                          `hotel_id` bigint(20) NOT NULL,
                          `user_id` bigint(20) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK_review_to_hotel` (`hotel_id`),
                          KEY `FK_review_to_user` (`user_id`),
                          CONSTRAINT `FK_review_to_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
                          CONSTRAINT `FK_review_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `review`
--
LOCK TABLES `review` WRITE;
INSERT INTO `review` VALUES (1,'서비스가 매우 만족스러웠습니다. 직원들이 친절하고 시설도 깨끗합니다.',0,4.5,1,1),(2,'오션 뷰가 정말 멋졌어요. 다시 방문하고 싶습니다.',0,5,2,2),(3,'가족 여행에 최고였습니다. 아이들이 좋아했어요.',0,4,3,3),(4,'조용하고 편안한 분위기에서 잘 쉬었습니다.',0,4.8,4,4),(5,'가격 대비 만족도가 높았습니다. 추천합니다.',0,4.2,5,5);
UNLOCK TABLES;


--
-- Table structure for table `reservation`
--
CREATE TABLE `reservation` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `check_in_date` date NOT NULL,
                               `check_out_date` date NOT NULL,
                               `discount` decimal(10,2) DEFAULT NULL,
                               `taxes` decimal(10,2) DEFAULT NULL,
                               `total_price` decimal(10,2) NOT NULL,
                               `room_id` bigint(20) NOT NULL,
                               `user_id` bigint(20) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK_reservation_to_room` (`room_id`),
                               KEY `FK_reservation_to_user` (`user_id`),
                               CONSTRAINT `FK_reservation_to_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
                               CONSTRAINT `FK_reservation_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation`
--
LOCK TABLES `reservation` WRITE;
INSERT INTO `reservation` VALUES (6,'2024-01-10','2024-01-12',50000.00,25000.00,575000.00,6,1),(7,'2024-02-15','2024-02-18',100000.00,50000.00,1200000.00,7,2),(8,'2024-03-20','2024-03-22',30000.00,15000.00,485000.00,8,3),(9,'2024-04-05','2024-04-07',70000.00,35000.00,665000.00,9,4),(10,'2024-05-25','2024-05-27',20000.00,10000.00,390000.00,10,5);
UNLOCK TABLES;


--
-- Table structure for table `payment`
--
CREATE TABLE `payment` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `card_user` varchar(100) DEFAULT NULL,
                           `country` varchar(100) DEFAULT NULL,
                           `cvc` varchar(10) DEFAULT NULL,
                           `expiration_date` date DEFAULT NULL,
                           `payment_name` varchar(100) DEFAULT NULL,
                           `payment_number` varchar(50) DEFAULT NULL,
                           `registration_date` datetime(6) DEFAULT NULL,
                           `user_id` bigint(20) NOT NULL,
                           `is_deleted` bit(1) DEFAULT 0,
                           PRIMARY KEY (`id`),
                           KEY `FK_payment_to_user` (`user_id`),
                           CONSTRAINT `FK_payment_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `payment`
--
LOCK TABLES `payment` WRITE;
INSERT INTO `payment` VALUES (1,'김민준','대한민국','123','2025-12-31','신한카드','1234-5678-9012-3456','2023-01-15 10:00:00.000000',1,0),(2,'이서연','대한민국','234','2026-11-30','국민카드','2345-6789-0123-4567','2023-02-20 11:30:00.000000',2,0),(3,'박준호','대한민국','345','2027-10-31','삼성카드','3456-7890-1234-5678','2023-03-25 14:00:00.000000',3,0),(4,'최지우','대한민국','456','2028-09-30','현대카드','4567-8901-2345-6789','2023-04-10 16:45:00.000000',4,0),(5,'정유진','대한민국','567','2029-08-31','우리카드','5678-9012-3456-7890','2023-05-12 18:20:00.000000',5,0);
UNLOCK TABLES;


--
-- Table structure for table `pay`
--
CREATE TABLE `pay` (
                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                       `payment_gateway` VARCHAR(20),
                       `redate` DATETIME(6),
                       `price` DECIMAL(10, 2),
                       `payment_id` BIGINT,
                       `user_id` BIGINT,
                       `reservation_id` BIGINT,
                       PRIMARY KEY (`id`),
                       CONSTRAINT `FK_pay_to_payment` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
                       CONSTRAINT `FK_pay_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                       CONSTRAINT `FK_pay_to_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `pay`
--
LOCK TABLES `pay` WRITE;
INSERT INTO `pay` VALUES (1,'카카오페이','2024-01-10 14:00:00.000000',575000.00,1,1,6),(2,'네이버페이','2024-02-15 16:30:00.000000',1200000.00,2,2,7),(3,'토스페이','2024-03-20 18:00:00.000000',485000.00,3,3,8),(4,'신용카드','2024-04-05 20:15:00.000000',665000.00,4,4,9),(5,'계좌이체','2024-05-25 22:00:00.000000',390000.00,5,5,10);
UNLOCK TABLES;
--=============
-- 호텔 도시 필수값으로 지정
--=============
ALTER TABLE booking_db.hotel MODIFY COLUMN city_id bigint NOT NULL;


























