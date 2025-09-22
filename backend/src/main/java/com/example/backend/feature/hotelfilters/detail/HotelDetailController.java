package com.example.backend.feature.hotelfilters.detail;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels/detail")
public class HotelDetailController {

    private final HotelDetailService hotelDetailService;

    /**
     * 호텔 상세 정보 조회
     * @param hotelId : 상세 조회할 호텔 ID
     * @return : HotelDetailDto
     *
     * 테스트 시 임시로 로그인 유저 ID 1 사용
     */
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDetailDto> getHotelDetail(
            @PathVariable Long hotelId
    ) {
        Long loginUserId = 1L; // 임시 로그인 유저
        HotelDetailDto detailDto = hotelDetailService.getHotelDetail(hotelId, loginUserId);
        return ResponseEntity.ok(detailDto);
    }
}