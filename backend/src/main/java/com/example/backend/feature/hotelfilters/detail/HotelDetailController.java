package com.example.backend.feature.hotelfilters.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels/detail")
public class HotelDetailController {

    private final HotelDetailService hotelDetailService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDetailDto> getHotelDetail(
            @PathVariable Long hotelId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long loginUserId = (userDetails != null) ? Long.parseLong(userDetails.getUsername()) : null;
        HotelDetailDto detailDto = hotelDetailService.getHotelDetail(hotelId, loginUserId);
        return ResponseEntity.ok(detailDto);
    }
}