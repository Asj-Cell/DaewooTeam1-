package com.example.backend.feature.hotelfilters.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor

/**
 * 사용자가 입력하는 필터의 종류 dto
 */
public class HotelFilterRequestDto {

    private String cityName;
    //무료 서비스 필터
    private Boolean breakfastIncluded;   // 조식 포함
    private Boolean freeParking;         // 무료 주차
    private Boolean freeWifi;            // 무료 와이파이
    private Boolean airportShuttlebus;   // 공항 셔틀버스
    private Boolean freeCancellation;    // 무료 취소

    // 편의시설 필터
    private Boolean frontDesk24;         // 24시 프론트데스크
    private Boolean airConditioner;      // 에어컨
    private Boolean fitnessCenter;       // 피트니스
    private Boolean pool;                // 수영장


    private Integer minGrade;             // 최소 성급
    private BigDecimal minprice;          //최소 가격
    private BigDecimal maxprice;          //최대 가격

}
