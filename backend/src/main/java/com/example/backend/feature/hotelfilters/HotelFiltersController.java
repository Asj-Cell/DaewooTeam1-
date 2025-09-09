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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelFiltersController {

    private final HotelFiltersService hotelFiltersService;

    @Operation(summary = "호텔 필터링 조회", description = "사용자가 선택한 조건에 맞는 호텔을 조회합니다.")
    @PostMapping("/filter")
    public ResponseEntity<List<HotelDto>> filterHotels(@RequestBody HotelFilterRequestDto request) {
        List<HotelDto> filteredHotels = hotelFiltersService.filterHotels(request);
        return ResponseEntity.ok(filteredHotels);
    }
}
