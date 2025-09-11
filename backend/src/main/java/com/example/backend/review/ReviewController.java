package com.example.backend.review;

import com.example.backend.common.util.ApiResponse;
import com.example.backend.review.dto.ReviewPageTotalInfoDto;
import com.example.backend.review.dto.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http://localhost:8888/api/hotels/1/reviews?page=0&size=2
//http://localhost:8888/api/hotels/1/reviews?page=1&size=5
//http://localhost:8888/api/hotels/1/reviews?page=0&size=3
@Tag(name = "Review API", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "호텔별 리뷰 조회", description = "특정 호텔에 대한 리뷰를 페이징하여 조회합니다.")
    @GetMapping("/{hotelId}/reviews")
    public ResponseEntity<ApiResponse<Page<ReviewResponseDto>>> getReviewsByHotel(@PathVariable Long hotelId, Pageable pageable) { // page, size, sort 파라미터를 자동으로 받음
        Page<ReviewResponseDto> reviewsByHotel = reviewService.getReviewsByHotel(hotelId, pageable);
        return ResponseEntity.ok(ApiResponse.success(reviewsByHotel));
    }

    @Operation(summary = "호텔별 리뷰 평균 점수 및 개수 ", description = "특정 호텔에 대한 total 정보를 조회합니다.")
    @GetMapping("/{hotelId}/total/info")
    public ResponseEntity<ApiResponse<ReviewPageTotalInfoDto>> getReviewsTotalInfoByHotel(@PathVariable Long hotelId) {
        ReviewPageTotalInfoDto reviewTotalCountAndRating = reviewService.getReviewTotalCountAndRating(hotelId);
        return ResponseEntity.ok(ApiResponse.success(reviewTotalCountAndRating));
    }

    @Operation(summary = "호텔별 리뷰 추가", description = "특정 호텔에 대한 리뷰를 추가")
    @PostMapping("/{hotelId}")
    public ResponseEntity<ApiResponse<String>> addReview() {
//        ReviewPageTotalInfoDto reviewTotalCountAndRating = reviewService.getReviewTotalCountAndRating(hotelId);
//        return ResponseEntity.ok(ApiResponse.success(reviewTotalCountAndRating));
        return null;
    }


}