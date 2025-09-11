// backend/src/main/java/com/example/backend/review/ReviewService.java
package com.example.backend.review;

import com.example.backend.review.dto.ReviewPageTotalInfoDto;
import com.example.backend.review.dto.ReviewResponseDto;
import com.example.backend.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewResponseDto> getReviewsByHotel(Long hotelId, Pageable pageable) {
        //페이징 처리된 리뷰 목록 조회
        Page<Review> reviewPage = reviewRepository.findByHotelIdOrderByIdDesc(hotelId, pageable);
        //Page<Review>를 Page<ReviewResponseDto>로 변환
        Page<ReviewResponseDto> reviewResponseDtoPage = reviewPage.map(review -> new ReviewResponseDto(review));
        // 최종 DTO에 담아 반환
        return reviewResponseDtoPage;
    }

    public ReviewPageTotalInfoDto getReviewTotalCountAndRating(Long hotelId){
        // 1. 특정 호텔의 전체 리뷰 개수 조회
        long totalReviews = reviewRepository.countByHotelId(hotelId);
        // 2. 특정 호텔의 평점 합계 조회
        Double totalRating = reviewRepository.findTotalRatingByHotelId(hotelId);
        // 3. 평균 평점 계산 (리뷰가 없는 경우 0점)
        double averageRating = (totalReviews > 0 && totalRating != null)
                ? Math.round((totalRating / totalReviews) * 10.0) / 10.0 // 소수점 첫째 자리까지 반올림
                : 0.0;
        return new ReviewPageTotalInfoDto(averageRating, totalReviews);
    }

    public void addReview(Review review) {

    }
}