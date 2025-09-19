package com.example.backend.feature.hotelfilters.detail;

import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.room.dto.RoomDto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class HotelDetailDto extends HotelDto {

    // 호텔의 상세 편의시설 (문자열 목록으로 내려주기)
    private List<String> amenities;

    // 호텔에 속한 방 목록
    private List<RoomDto> rooms;

    // 호텔 소개/설명
    private String overview;

    // 생성자
    public HotelDetailDto(Long id,
                          String name,
                          String address,
                          Integer grade,
                          int amenitiesCount,
                          BigDecimal price,
                          double rating,
                          String representativeImage,
                          boolean isFavorite,
                          List<String> amenities,
                          List<RoomDto> rooms,
                          String overview) {
        super(id, name, address, grade, amenitiesCount, price, rating, representativeImage, isFavorite, reviewCount);
        this.amenities = amenities;
        this.rooms = rooms;
        this.overview = overview;
    }
}
