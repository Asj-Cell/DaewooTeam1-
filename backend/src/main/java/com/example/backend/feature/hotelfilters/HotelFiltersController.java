package com.example.backend.feature.hotelfilters;

import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelFiltersController {

    private final HotelFiltersService hotelFiltersService;

    /**
     * 호텔 필터 조회
     * POST 방식으로 필터 요청
     * @param request : 사용자가 선택한 필터
     * @param page : 페이지 번호 (0부터 시작)
     * @param size : 한 페이지에 가져올 호텔 개수
     * @return : 호텔 리스트 + 전체 호텔 수
     */
    @PostMapping("/filter")
    public Map<String, Object> filterHotels(
            @RequestBody HotelFilterRequestDto request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
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