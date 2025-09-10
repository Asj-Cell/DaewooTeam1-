package com.example.backend.hotel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
public class HotelSearchResponseDto {
    private Long hotelId;
    private String name;
    private String address;
    private BigDecimal grade;
    private String imageUrl;
    private BigDecimal minPrice;

    // JPQL에서 new 생성자를 사용하기 위한 생성자
    public HotelSearchResponseDto(Long hotelId, String name, String address, BigDecimal grade, String imageUrl, BigDecimal minPrice) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.grade = grade;
        this.imageUrl = imageUrl;
        this.minPrice = minPrice;
    }
}