package com.example.backend.review;

import com.example.backend.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 호텔의 리뷰를 최신순으로 페이징하여 조회
    Page<Review> findByHotelIdOrderByIdDesc(Long hotelId, Pageable pageable);

    // 특정 호텔의 전체 리뷰 수 조회
    long countByHotelId(Long hotelId);

    // 특정 호텔의 평점 합계 조회 (평균을 계산하기 위함)
    @Query("SELECT SUM(r.userRatingScore) FROM Review r WHERE r.hotel.id = :hotelId")
    Double findTotalRatingByHotelId(@Param("hotelId") Long hotelId);
}
