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
INSERT INTO city (city_name) VALUES
                                 ('서울'),
                                 ('부산'),
                                 ('제주'),
                                 ('인천'),
                                 ('경주');

-- Hotel 테이블 INSERT (checkin_time, checkout_time을 TIME 형식으로 수정)
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id) VALUES
                                                                                                                  ('신라호텔', 5, '최고급 호텔', 37.5558, 127.0053, '서울시 중구 동호로 249', '15:00:00', '12:00:00', 1),
                                                                                                                  ('파라다이스 호텔 부산', 5, '해운대 해변에 위치한 럭셔리 호텔', 35.1598, 129.1603, '부산시 해운대구 해운대해변로 296', '15:00:00', '11:00:00', 2),
                                                                                                                  ('롯데호텔 제주', 5, '중문관광단지에 위치한 리조트형 호텔', 33.2476, 126.4107, '제주특별자치도 서귀포시 중문관광로72번길 35', '14:00:00', '11:00:00', 3),
                                                                                                                  ('경원재 앰배서더 인천', 5, '한옥 호텔', 37.3947, 126.6384, '인천시 연수구 테크노파크로 200', '15:00:00', '11:00:00', 4),
                                                                                                                  ('힐튼 경주', 5, '보문호수에 위치한 호텔', 35.8400, 129.2818, '경북 경주시 보문로 484-7', '15:00:00', '11:00:00', 5);
-- Hotel_Image 테이블 INSERT
INSERT INTO hotel_image (image_url, size, sequence, hotel_id) VALUES
                                                                  ('http://example.com/images/shilla_hotel_1.jpg', 1024, 1, 11),
                                                                  ('http://example.com/images/paradise_busan_1.jpg', 2048, 1, 12),
                                                                  ('http://example.com/images/lotte_jeju_1.jpg', 1536, 1, 13),
                                                                  ('http://example.com/images/gyeongwonjae_incheon_1.jpg', 1280, 1, 14),
                                                                  ('http://example.com/images/hilton_gyeongju_1.jpg', 1800, 1, 15);

-- Room 테이블 INSERT
INSERT INTO room (room_number, price, name, view, bed, max_guests, hotel_id) VALUES
                                                                                 ('101', 300000, '디럭스 룸', '시티 뷰', '더블', 2, 11),
                                                                                 ('202', 450000, '스위트 룸', '오션 뷰', '킹', 3, 12),
                                                                                 ('303', 250000, '패밀리 룸', '가든 뷰', '트윈', 4, 13),
                                                                                 ('404', 350000, '이그제큐티브 룸', '파크 뷰', '퀸', 2, 14),
                                                                                 ('505', 200000, '스탠다드 룸', '마운틴 뷰', '싱글', 1, 15);


-- Reservation 테이블 INSERT (수정된 컬럼명 적용)
INSERT INTO reservation (check_in_date, check_out_date, discount, taxes, total_price, room_id, user_id) VALUES
                                                                                                            ('2024-01-10', '2024-01-12', 50000, 25000, 575000, 1, 1),
                                                                                                            ('2024-02-15', '2024-02-18', 100000, 50000, 1200000, 2, 2),
                                                                                                            ('2024-03-20', '2024-03-22', 30000, 15000, 485000, 3, 3),
                                                                                                            ('2024-04-05', '2024-04-07', 70000, 35000, 665000, 4, 4),
                                                                                                            ('2024-05-25', '2024-05-27', 20000, 10000, 390000, 5, 5);

-- TravelPackage 테이블 INSERT
ININSERT INTO travel_package (city_id, title, description, price, st_date, end_date) VALUES
(1, '서울 2박 3일 시티 투어', '서울의 주요 관광지를 둘러보는 패키지', 350000, '2024-06-10', '2024-06-12'),
(2, '부산 3박 4일 미식 여행', '부산의 맛집을 탐방하는 미식 여행 패키지', 450000, '2024-07-15', '2024-07-18'),
(3, '제주 4박 5일 힐링 여행', '제주의 자연 속에서 힐링하는 여행', 550000, '2024-08-20', '2024-08-24'),
(4, '인천 1박 2일 차이나타운 투어', '인천 차이나타운과 개항장 거리를 둘러보는 패키지', 150000, '2024-09-05', '2024-09-06'),
(5, '경주 2박 3일 역사 여행', '신라의 역사를 따라 떠나는 여행', 250000, '2024-10-10', '2024-10-12');

-- Package_Image 테이블 INSERT
INSERT INTO package_image (image_url, package_id) VALUES
                                                      ('http://example.com/images/seoul_tour.jpg', 1),
                                                      ('http://example.com/images/busan_food.jpg', 2),
                                                      ('http://example.com/images/jeju_healing.jpg', 3),
                                                      ('http://example.com/images/incheon_chinatown.jpg', 4),
                                                      ('http://example.com/images/gyeongju_history.jpg', 5);

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
INSERT INTO "user" (user_name, email, password, phone_number, address, birth_date, image_url, background_image_url) VALUES
                                                                                                                        ('김민준', 'minjun.kim@example.com', 'password123', '010-1234-5678', '서울시 강남구', '1990-01-15', 'http://example.com/images/minjun.jpg', 'http://example.com/images/minjun_bg.jpg'),
                                                                                                                        ('이서연', 'seoyeon.lee@example.com', 'password456', '010-2345-6789', '부산시 해운대구', '1992-05-20', 'http://example.com/images/seoyeon.jpg', 'http://example.com/images/seoyeon_bg.jpg'),
                                                                                                                        ('박준호', 'junho.park@example.com', 'password789', '010-3456-7890', '인천시 연수구', '1988-08-10', 'http://example.com/images/junho.jpg', 'http://example.com/images/junho_bg.jpg'),
                                                                                                                        ('최지우', 'jiwoo.choi@example.com', 'password101', '010-4567-8901', '대구시 수성구', '1995-11-25', 'http://example.com/images/jiwoo.jpg', 'http://example.com/images/jiwoo_bg.jpg'),
                                                                                                                        ('정유진', 'yujin.jung@example.com', 'password202', '010-5678-9012', '광주시 서구', '1998-03-30', 'http://example.com/images/yujin.jpg', 'http://example.com/images/yujin_bg.jpg');
-- 임시 리뷰 인서트
-- 신라호텔 (hotel_id = 1) 리뷰
INSERT INTO review (user_id, content, user_rating_score, report_yn, hotel_id) VALUES
                                                                                  (1, '서비스가 매우 만족스러웠습니다. 직원들이 친절하고 시설도 깨끗합니다.', 4.5, false, 1),
                                                                                  (2, '오션 뷰가 정말 멋졌어요. 다시 방문하고 싶습니다.', 5.0, false, 2),
                                                                                  (3, '가족 여행에 최고였습니다. 아이들이 좋아했어요.', 4.0, false, 3),
                                                                                  (4, '조용하고 편안한 분위기에서 잘 쉬었습니다.', 4.8, false, 4),
                                                                                  (5, '가격 대비 만족도가 높았습니다. 추천합니다.', 4.2, false, 5);
-- 임시 편의시설 인서트
INSERT INTO amenities (front_desk24, outdoor_pool, indoor_pool, spa_wellness_center, restaurant, roomservice, fitness_center, bar_lounge, tea_coffee_machine, airconditioning, hotel_id) VALUES
                                                                                                                                                                                             (true, true, true, true, true, true, true, true, true, true, 11),
                                                                                                                                                                                             (true, true, false, true, true, true, true, true, true, true, 12),
                                                                                                                                                                                             (true, true, true, true, true, true, true, true, true, true, 13),
                                                                                                                                                                                             (true, false, false, false, true, true, false, false, true, true, 14),
                                                                                                                                                                                             (true, true, true, true, true, true, true, true, true, true, 15);
-- 임시 무료 서비스 인서트
INSERT INTO freebies (Breakfast_included, free_parking, free_wifi, Airport_shuttlebus, free_cancellation, hotel_id) VALUES
                                                                                                                        (true, true, true, true, true, 11),
                                                                                                                        (false, true, true, false, true, 12),
                                                                                                                        (true, true, true, true, false, 13),
                                                                                                                        (true, true, true, false, true, 14),
                                                                                                                        (false, true, true, false, true, 15);
-- 임시 편의시설 인서트
INSERT INTO amenities (front_desk24, outdoor_pool, indoor_pool, spa_wellness_center, restaurant, roomservice, fitness_center, bar_lounge, tea_coffee_machine, airconditioning, hotel_id) VALUES
          (1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1),
          (1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 2),
          (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3),
          (1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 4),
          (1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 5);

-- 임시 무료 서비스 인서트
INSERT INTO freebies (Breakfast_included, free_parking, free_wifi, Airport_shuttlebus, free_cancellation, hotel_id) VALUES
          (1, 1, 1, 0, 1, 1),
          (1, 0, 1, 0, 1, 2),
          (1, 1, 1, 1, 1, 3),
          (0, 0, 1, 1, 1, 4),
          (1, 1, 0, 0, 1, 5);


INSERT INTO payment (payment_name, payment_number, expiration_date, cvc, card_user, country, registration_date, user_id) VALUES
                                                                                                                             ('신한카드', '1234-5678-9012-3456', '2025-12-31', '123', '김민준', '대한민국', '2023-01-15 10:00:00', 1),
                                                                                                                             ('국민카드', '2345-6789-0123-4567', '2026-11-30', '234', '이서연', '대한민국', '2023-02-20 11:30:00', 2),
                                                                                                                             ('삼성카드', '3456-7890-1234-5678', '2027-10-31', '345', '박준호', '대한민국', '2023-03-25 14:00:00', 3),
                                                                                                                             ('현대카드', '4567-8901-2345-6789', '2028-09-30', '456', '최지우', '대한민국', '2023-04-10 16:45:00', 4),
                                                                                                                             ('우리카드', '5678-9012-3456-7890', '2029-08-31', '567', '정유진', '대한민국', '2023-05-12 18:20:00', 5);


INSERT INTO pay (payment_gateway, redate, price, payment_id, user_id, reservation_id) VALUES
                                                                                          ('카카오페이', '2024-01-10 14:00:00', 575000, 1, 1, 1),
                                                                                          ('네이버페이', '2024-02-15 16:30:00', 1200000, 2, 2, 2),
                                                                                          ('토스페이', '2024-03-20 18:00:00', 485000, 3, 3, 3),
                                                                                          ('신용카드', '2024-04-05 20:15:00', 665000, 4, 4, 4),
                                                                                          ('계좌이체', '2024-05-25 22:00:00', 390000, 5, 5, 5);
--=============
-- 호텔 도시 필수값으로 지정
--=============
ALTER TABLE booking_db.hotel MODIFY COLUMN city_id bigint NOT NULL;
