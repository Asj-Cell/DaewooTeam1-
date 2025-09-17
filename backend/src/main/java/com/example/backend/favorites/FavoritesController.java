package com.example.backend.favorites;

import com.example.backend.feature.hotelfilters.dto.HotelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @GetMapping("/api/favorites")
    public ResponseEntity<List<HotelDto>> getFavoriteHotels() {
        Long loginUserId = 1L; // 현재 로그인한 유저 ID (임시)
        List<HotelDto> favoriteHotels = favoritesService.getFavoriteHotels(loginUserId);
        return ResponseEntity.ok(favoriteHotels);
    }
}
