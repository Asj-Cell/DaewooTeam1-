//package com.example.backend.hotel;
//
//import com.example.backend.common.util.ApiResponse;
//import com.example.backend.hotel.dto.HotelSearchRequestDto;
//import com.example.backend.hotel.dto.HotelSearchResponseDto;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//// http://localhost:8888/api/hotels/search?destination=
//// 신라&checkInDate=2025-10-11&checkOutDate=2025-10-13&guestCount=2&page=0&size=4
//@Tag(name = "Hotel API", description = "호텔 관련 API")
//@RestController
//@RequestMapping("/api/hotels")
//@RequiredArgsConstructor
//public class HotelController {
//
//    private final HotelService hotelService;
//
//    @Operation(summary = "호텔 검색", description = "지정한 조건에 맞는 호텔 목록을 페이징하여 조회합니다.")
//    @GetMapping("/search")
//    public ResponseEntity<ApiResponse<Page<HotelSearchResponseDto>>> searchHotels(
//            @ModelAttribute HotelSearchRequestDto requestDto,
//            Pageable pageable) {
//        Page<HotelSearchResponseDto> results = hotelService.searchHotels(requestDto, pageable);
//        return ResponseEntity.ok(ApiResponse.success(results));
//    }
//}