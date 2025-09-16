package com.example.backend.feature.hotelfilters;

import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelFiltersController {
    private final HotelFiltersService hotelFiltersService;

    /**
     * 호텔 필터 조회
     * POST 방식으로 필터 요청
     * @param request : 사용자가 선택한 필터
     * @param page : 페이지 번호 (0부터 시작)
     * @param size : 한 페이지에 가져올 호텔 개수
     * @return : 호텔 리스트 + 전체 호텔 수
     *
     *
     * http://localhost:8888/api/hotels/filter?page=0&size=4 주소
     *
     * 모든 조건, 임시(아이디 순으로) 첫번째 페이지에 4개씩 불러오는
     * {
     *   "breakfastIncluded": null,
     *   "freeParking": null,
     *   "freeWifi": null,
     *   "airportShuttlebus": null,
     *   "freeCancellation": null,
     *   "frontDesk24": null,
     *   "airConditioner": null,
     *   "fitnessCenter": null,
     *   "Pool": null,
     *   "minGrade": null,
     *   "minprice": null,
     *   "maxprice": null,
     *   "page": 0,
     *   "size": 4
     *
     *
     *
     *   정렬관련 손다시 봐야함
     * }
     */

    @PostMapping("/filter")
    public Map<String, Object> filterHotels(
            @RequestBody HotelFilterRequestDto request,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HotelDto> hotelPage = hotelFiltersService.filterHotels(request, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("hotels", hotelPage.getContent());
        response.put("totalHotels", hotelPage.getTotalElements());
        response.put("currentPage", hotelPage.getNumber());
        response.put("totalPages", hotelPage.getTotalPages());

        return response;
    }
}