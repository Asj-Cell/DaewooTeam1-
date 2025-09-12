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



-- City 테이블 INSERT
INSERT INTO city (city_name)
VALUES ('서울');
INSERT INTO city (city_name)
VALUES ('부산');
INSERT INTO city (city_name)
VALUES ('제주');
INSERT INTO city (city_name)
VALUES ('강릉');
INSERT INTO city (city_name)
VALUES ('경주');

-- Hotel 테이블 INSERT (checkin_time, checkout_time을 TIME 형식으로 수정)
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id)
VALUES ('신라호텔', 5.0, '최고급 시설과 서비스를 자랑하는 서울의 랜드마크 호텔', 37.5558, 127.0053, '서울 중구 동호로 249', '15:00:00', '11:00:00', 1);
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id)
VALUES ('파라다이스 호텔 부산', 4.8, '해운대 해변 바로 앞에 위치한 럭셔리 리조트', 35.159, 129.159, '부산 해운대구 해운대해변로 296', '15:00:00', '11:00:00',
        2);
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id)
VALUES ('롯데호텔 제주', 4.5, '중문관광단지에 위치한 가족 친화적인 리조트 호텔', 33.249, 126.409, '제주 서귀포시 중문관광로72번길 35', '15:00:00', '11:00:00',
        3);
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id)
VALUES ('세인트존스 호텔', 4.2, '강문 해변의 멋진 전망을 자랑하는 강릉의 인기 호텔', 37.794, 128.92, '강원 강릉시 창해로 307', '15:00:00', '11:00:00', 4);
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id)
VALUES ('힐튼 경주', 4.6, '보문호수 옆에 자리한 경주의 대표적인 특급 호텔', 35.839, 129.28, '경북 경주시 보문로 484-7', '15:00:00', '11:00:00', 5);

-- Hotel_Image 테이블 INSERT
INSERT INTO hotel_image (image_url, size, sequence, hotel_id)
VALUES ('https://example.com/images/shilla_main.jpg', 1200, 1, 1);
INSERT INTO hotel_image (image_url, size, sequence, hotel_id)
VALUES ('https://example.com/images/shilla_sub1.jpg', 800, 2, 1);
INSERT INTO hotel_image (image_url, size, sequence, hotel_id)
VALUES ('https://example.com/images/paradise_main.jpg', 1500, 1, 2);
INSERT INTO hotel_image (image_url, size, sequence, hotel_id)
VALUES ('https://example.com/images/lotte_jeju_main.jpg', 1350, 1, 3);
INSERT INTO hotel_image (image_url, size, sequence, hotel_id)
VALUES ('https://example.com/images/stjohns_main.jpg', 1100, 1, 4);

-- Room 테이블 INSERT
INSERT INTO room (price, name, view, bed, max_guests, hotel_id)
VALUES (350000.00, '디럭스 룸', '시티 뷰', '더블 베드 1개', 2, 1);
INSERT INTO room (price, name, view, bed, max_guests, hotel_id)
VALUES (450000.00, '이그제큐티브 룸', '남산 뷰', '킹 베드 1개', 2, 1);
INSERT INTO room (price, name, view, bed, max_guests, hotel_id)
VALUES (280000.00, '오션 테라스 룸', '오션 뷰', '더블 베드 2개', 4, 2);
INSERT INTO room (price, name, view, bed, max_guests, hotel_id)
VALUES (220000.00, '디럭스 가든 룸', '가든 뷰', '퀸 베드 1개', 2, 3);
INSERT INTO room (price, name, view, bed, max_guests, hotel_id)
VALUES (180000.00, '슈페리어 더블', '오션 뷰', '더블 베드 1개', 2, 4);

-- Reservation 테이블 INSERT (수정된 컬럼명 적용)
INSERT INTO reservation (check_in_date, check_out_date, discount, taxes, total_price, room_id)
VALUES ('2025-10-10', '2025-10-15', 0.00, 35000.00, 385000.00, 1);
INSERT INTO reservation (check_in_date, check_out_date, discount, taxes, total_price, room_id)
VALUES ('2025-11-01', '2025-11-03', 10000.00, 27000.00, 297000.00, 3);
INSERT INTO reservation (check_in_date, check_out_date, discount, taxes, total_price, room_id)
VALUES ('2025-12-24', '2025-12-26', 0.00, 22000.00, 242000.00, 4);
INSERT INTO reservation (check_in_date, check_out_date, discount, taxes, total_price, room_id)
VALUES ('2026-01-05', '2026-01-10', 5000.00, 17500.00, 192500.00, 5);
INSERT INTO reservation (check_in_date, check_out_date, discount, taxes, total_price, room_id)
VALUES ('2025-09-20', '2025-09-22', 20000.00, 43000.00, 473000.00, 2);

-- TravelPackage 테이블 INSERT
INSERT INTO travel_package (title, description, price, st_date, end_date, city_id)
VALUES ('[서울 시티투어] 궁궐과 미식의 조화', '경복궁, 창덕궁 등 서울의 5대 궁궐을 둘러보고, 광장시장에서 다양한 먹거리를 즐기는 알찬 당일치기 패키지입니다.', 89000.00,
        '2025-10-05', '2025-10-05', 1);
INSERT INTO travel_package (title, description, price, st_date, end_date, city_id)
VALUES ('[부산 힐링여행] 바다와 야경을 한번에', '해운대 해변에서의 여유로운 시간과 더베이101의 화려한 야경을 함께 즐길 수 있는 2박 3일 힐링 코스입니다.', 250000.00,
        '2025-11-10', '2025-11-12', 2);
INSERT INTO travel_package (title, description, price, st_date, end_date, city_id)
VALUES ('[제주도 완전정복] 동서남북 핵심투어', '3박 4일간 제주도의 동쪽, 서쪽, 남쪽, 북쪽의 핵심 명소들을 모두 둘러보는 실속있는 패키지입니다.', 450000.00, '2025-09-28',
        '2025-10-01', 3);
INSERT INTO travel_package (title, description, price, st_date, end_date, city_id)
VALUES ('[강릉 감성여행] 커피와 바다', '안목해변 카페거리에서 즐기는 커피 한 잔의 여유와 BTS 버스정류장 등 감성적인 포토 스팟을 방문하는 1박 2일 코스.', 180000.00,
        '2025-10-18', '2025-10-19', 4);
INSERT INTO travel_package (title, description, price, st_date, end_date, city_id)
VALUES ('[경주 역사기행] 천년의 역사를 따라서', '불국사, 석굴암, 첨성대 등 신라의 역사가 살아숨쉬는 경주의 유적지들을 전문가의 설명과 함께 둘러보는 2박 3일 패키지.', 220000.00,
        '2025-11-22', '2025-11-24', 5);

-- Package_Image 테이블 INSERT
INSERT INTO package_image (image_url, package_id)
VALUES ('https://example.com/images/seoul_palace.jpg', 1);
INSERT INTO package_image (image_url, package_id)
VALUES ('https://example.com/images/seoul_market.jpg', 1);
INSERT INTO package_image (image_url, package_id)
VALUES ('https://example.com/images/busan_haeundae.jpg', 2);
INSERT INTO package_image (image_url, package_id)
VALUES ('https://example.com/images/jeju_seongsan.jpg', 3);
INSERT INTO package_image (image_url, package_id)
VALUES ('https://example.com/images/gangneung_coffee.jpg', 4);


-- =================================================================
-- 3. Package 테이블: 여행 패키지 정보 추가 (id 열 제거)
-- city_id는 위에서 생성된 City의 id를 그대로 사용합니다.
-- =================================================================
-- '여행 더보기' (MainPackage) 용 데이터 (id=1로 자동 생성될 것)
INSERT INTO package (title, description, price, st_date, end_date, city_id)
VALUES ('말라카 투어', '시간이 멈춘 도시, 말라카에서 과거로의 여행을 떠나보세요. 유네스코 세계문화유산을 거닐며 역사와 낭만을 동시에 느낄 수 있습니다.', 700.00, '2025-10-01',
        '2025-10-05', 5);

-- '여행에 빠지다' (Popular Packages) 용 데이터 (id=2, 3, 4로 자동 생성될 것)
INSERT INTO package (title, description, price, st_date, end_date, city_id)
VALUES ('Melbourne Journey', '활기찬 도시 멜버른의 매력에 빠져보세요.', 130000.00, '2025-11-10', '2025-11-15', 4),
       ('파리 Adventure', '예술과 낭만의 도시 파리를 탐험하는 시간.', 150000.00, '2025-12-01', '2025-12-06', 2),
       ('런던 Classic', '전통과 현대가 공존하는 런던의 모든 것.', 130000.00, '2025-12-20', '2025-12-25', 3);


-- =================================================================
-- 4. PackageImage 테이블: 패키지 이미지 URL 추가
-- package_id는 위에서 생성된 Package의 id (1='말라카', 2='멜버른' 등)를 그대로 사용합니다.
-- =================================================================
INSERT INTO packageimage (package_id, image_url)
VALUES (1, 'https://example.com/images/malacca_tour_1.jpg'),
       (1, 'https://example.com/images/malacca_tour_2.jpg'),
       (1, 'https://example.com/images/malacca_tour_3.jpg'),
       (2, 'https://example.com/images/melbourne_journey.jpg'),
       (3, 'https://example.com/images/paris_adventure.jpg'),
       (4, 'https://example.com/images/london_classic.jpg');

-- 임시 유저 인서트
INSERT INTO user (user_name, email, password, phone_number, address, birth_date, image_url)
VALUES ('김철수', 'chulsoo@example.com', 'password123', '010-1111-1111', '서울시 강남구', '1990-05-10',
        'https://example.com/images/user1.jpg'),
       ('이영희', 'younghee@example.com', 'password123', '010-2222-2222', '서울시 송파구', '1988-03-22',
        'https://example.com/images/user2.jpg'),
       ('박민수', 'minsu@example.com', 'password123', '010-3333-3333', '경기도 성남시', '1995-07-15',
        'https://example.com/images/user3.jpg'),
       ('최지은', 'jieun@example.com', 'password123', '010-4444-4444', '인천시 부평구', '1992-12-01',
        'https://example.com/images/user4.jpg'),
       ('정다은', 'daeun@example.com', 'password123', '010-5555-5555', '부산시 해운대구', '1998-06-30',
        'https://example.com/images/user5.jpg'),
       ('한성우', 'sungwoo@example.com', 'password123', '010-6666-6666', '대구시 수성구', '1991-11-11',
        'https://example.com/images/user6.jpg'),
       ('김하늘', 'haneul@example.com', 'password123', '010-7777-7777', '서울시 마포구', '1993-02-18',
        'https://example.com/images/user7.jpg'),
       ('이준호', 'junho@example.com', 'password123', '010-8888-8888', '경기도 고양시', '1996-09-05',
        'https://example.com/images/user8.jpg'),
       ('송민지', 'minji@example.com', 'password123', '010-9999-9999', '광주시 북구', '1994-04-20',
        'https://example.com/images/user9.jpg'),
       ('강태훈', 'taehoon@example.com', 'password123', '010-0000-0000', '대전시 유성구', '1989-08-12',
        'https://example.com/images/user10.jpg');

-- 임시 리뷰 인서트
-- 신라호텔 (hotel_id = 1) 리뷰
INSERT INTO review (user_id, hotel_id, content, user_rating_score, report_yn)
VALUES (1, 1, '최고의 경험이었습니다. 직원분들이 정말 친절해요.', 5.0, FALSE),
       (2, 1, '룸 컨디션이 매우 깨끗하고 조용해서 좋았습니다.', 4.5, FALSE),
       (3, 1, '조식 뷔페가 기대 이상이었어요. 종류도 다양하고 맛있습니다.', 4.8, FALSE);

-- 파라다이스 호텔 부산 (hotel_id = 2) 리뷰
INSERT INTO review (user_id, hotel_id, content, user_rating_score, report_yn)
VALUES (4, 2, '해운대 뷰가 환상적입니다. 위치가 정말 최고네요.', 5.0, FALSE),
       (5, 2, '가격대가 좀 있지만, 그만한 가치를 하는 곳입니다. 수영장이 특히 좋았어요.', 4.7, FALSE);

-- 롯데호텔 제주 (hotel_id = 3) 리뷰
INSERT INTO review (user_id, hotel_id, content, user_rating_score, report_yn)
VALUES (6, 3, '가족 여행으로 완벽한 숙소입니다. 아이들이 정말 좋아했어요.', 4.9, FALSE),
       (7, 3, '시설이 약간 오래된 느낌이 들었지만, 전반적으로 만족합니다.', 3.8, FALSE);

-- 세인트존스 호텔 (hotel_id = 4) 리뷰
INSERT INTO review (user_id, hotel_id, content, user_rating_score, report_yn)
VALUES (8, 4, '강문해변 바로 앞이라 산책하기 너무 좋았어요. 방에서 보는 일출이 멋집니다.', 4.6, FALSE);

-- 힐튼 경주 (hotel_id = 5) 리뷰
INSERT INTO review (user_id, hotel_id, content, user_rating_score, report_yn)
VALUES (9, 5, '보문단지 중심에 있어서 경주 여행하기에 위치가 편리합니다.', 4.3, FALSE),
       (10, 5, '수영장이 넓고 관리가 잘 되어 있어서 아이들과 놀기 좋았습니다.', 4.4, FALSE);
-- 임시 편의시설 인서트
INSERT INTO amenities (front_desk24, outdoor_pool, indoor_pool, spa_wellness_center, restaurant, roomservice,
                       fitness_center, bar_lounge, tea_coffee_machine, airconditioning, hotel_id)
VALUES (1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1),
       (1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 2),
       (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3),
       (1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 4),
       (1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 5);

-- 임시 무료 서비스 인서트
INSERT INTO freebies (Breakfast_included, free_parking, free_wifi, Airport_shuttlebus, free_cancellation, hotel_id)
VALUES (1, 1, 1, 0, 1, 1),
       (1, 0, 1, 0, 1, 2),
       (1, 1, 1, 1, 1, 3),
       (0, 0, 1, 1, 1, 4),
       (1, 1, 0, 0, 1, 5);



--=============
-- 호텔 도시 필수값으로 지정
--=============
ALTER TABLE booking_db.hotel MODIFY COLUMN city_id bigint NOT NULL;
