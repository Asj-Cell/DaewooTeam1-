package com.example.backend.hotel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
public class HotelSearchRequestDto {

    private String destination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkInDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkOutDate;

    private Integer guestCount;

    private Integer roomCount; // 일단 받아만 둠

    // 가격도 나중에 추가 해야 하나
}