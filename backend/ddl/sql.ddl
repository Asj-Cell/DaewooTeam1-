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















-- 이 데이터는 테스트 용으로 ㄱㄱㄱㄱㄱ

 -- 데이터 삽입 순서: City -> Hotel -> TravelPackage -> PackageImage

-- =================================================================
-- 1. City 테이블: 도시 정보 추가 (id 열 제거)
-- =================================================================
INSERT INTO city (city_name)
VALUES ('서울'),
       ('파리'),
       ('런던'),
       ('멜버른'),
       ('말라카');


-- =================================================================
-- 2. Hotel 테이블: 호텔 정보 추가 (id 열 제거)
-- city_id는 위에서 생성된 City의 id (1='서울', 2='파리')를 그대로 사용합니다.
-- =================================================================
INSERT INTO hotel (name, grade, overview, latitude, longitude, address, checkin_time, checkout_time, city_id)
VALUES ('신라스테이 플러스', 5, '도심 속 휴식처', 37.5665, 126.9780, '서울 중구', '2025-01-01 15:00:00', '2025-01-02 11:00:00', 1),
       ('파리 메리어트 호텔', 4, '에펠탑 근처', 48.8584, 2.2945, '파리 7구', '2025-01-01 14:00:00', '2025-01-02 12:00:00', 2);


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


--=============
-- 호텔 도시 필수값으로 지정
--=============
ALTER TABLE booking_db.hotel MODIFY COLUMN city_id bigint NOT NULL;
