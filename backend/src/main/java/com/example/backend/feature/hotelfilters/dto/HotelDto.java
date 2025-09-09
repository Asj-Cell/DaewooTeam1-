package com.example.backend.feature.hotelfilters.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 두번째 페이지에 화면에 보여줄 호텔 정보들
 * 별점과 찜 여부 보류
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private Long id;
    private String name;
    private String cityName;
    private int grade;
//    private int amenitiesCount;
    private BigDecimal price;
    private String imageUrl;
}
