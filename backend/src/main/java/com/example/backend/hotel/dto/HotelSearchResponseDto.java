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
    private Double grade;
    private String imageUrl;
    private Double minPrice;

//    // JPQL에서 new 생성자를 사용하기 위한 생성자
//    public HotelSearchResponseDto(Long hotelId, String name, String address, Double grade, String imageUrl, Double minPrice) {
//        this.hotelId = hotelId;
//        this.name = name;
//        this.address = address;
//        this.grade = grade;
//        this.imageUrl = imageUrl;
//        this.minPrice = minPrice;
//    }
}